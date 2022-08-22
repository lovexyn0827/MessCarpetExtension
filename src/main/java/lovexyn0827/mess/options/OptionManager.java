package lovexyn0827.mess.options;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Maps;
import com.mojang.brigadier.context.CommandContext;

import io.netty.buffer.Unpooled;
import lovexyn0827.mess.MessMod;
import lovexyn0827.mess.command.CommandUtil;
import lovexyn0827.mess.mixins.WorldSavePathMixin;
import lovexyn0827.mess.network.Channels;
import lovexyn0827.mess.options.RangeParser.ChunkStatusRange.ChunkStatusSorter;
import lovexyn0827.mess.rendering.BlockInfoRenderer;
import lovexyn0827.mess.rendering.BlockInfoRenderer.ShapeType;
import lovexyn0827.mess.rendering.hud.AlignMode;
import lovexyn0827.mess.util.access.AccessingPath;
import lovexyn0827.mess.util.i18n.I18N;
import lovexyn0827.mess.util.i18n.Language;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

/**
 * <p>There are three layers in the option storage, that is, hard-coded global default values, global option value, and the save-local values.</p>
 * All fields and methods here are declared static in order to speed up option reading.
 * @author lovexyn0827
 * Date: April 2, 2022
 */
// TODO Now the option manager is pretty messy!
public class OptionManager{
	private static final File GLOBAL_OPTION_FILE = new File(FabricLoader.getInstance().getGameDir().toString() + "/mcwmem.prop");
	private static final Properties GLOBAL_OPTION_SERIALIZER = new Properties();
	public static final List<Field> OPTIONS = Stream.of(OptionManager.class.getFields())
			.filter((f) -> f.isAnnotationPresent(Option.class))
			.sorted((a, b) -> Comparator.<String>naturalOrder().compare(a.getName(), b.getName()))
			.collect(Collectors.toList());
	private static File localOptionFile;
	private static Properties localOptionSerializer;
	
	/**
	 * Actions taken right after an option is set to a given value.
	 */
	public static final Map<String, BiConsumer<String, CommandContext<ServerCommandSource>>> CUSTOM_APPLICATION_BEHAVIORS = Maps.newHashMap();
	
	@Option(defaultValue = "STANDARD", 
			parserClass = AccessingPath.InitializationStrategy.Parser.class)
	public static AccessingPath.InitializationStrategy accessingPathInitStrategy;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean antiHostCheating;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean attackableTnt;
	
	@Option(defaultValue = "NORMALLY", 
			parserClass = BlockInfoRenderer.FrozenUpdateMode.Parser.class)
	public static BlockInfoRenderer.FrozenUpdateMode blockInfoRendererUpdateInFrozenTicks;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean blockPlacementHistory;
	
	@Option(defaultValue = "COLLISION", 
			parserClass = ShapeType.Parser.class)
	public static ShapeType blockShapeToBeRendered;

	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean commandExecutionRequirment;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean craftingTableBUD;
	
	@Option(defaultValue = "0.05", 
			suggestions = {"0.05", "0.10"}, 
			parserClass = FloatParser.Positive.class)
	public static float creativeUpwardsSpeed;
	
	@Option(defaultValue = "false", 
			experimental = true, 
			parserClass = BooleanParser.class)
	public static boolean debugStickSkipsInvaildState;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean disableChunkLoadingCheckInCommands;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean disableExplosionExposureCalculation;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean disableProjectileRandomness;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean enabledTools;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean endEyeTeleport;
	
	@Option(defaultValue = "false", 
			experimental = true, 
			parserClass = BooleanParser.class)
	public static boolean entityExplosionInfluence;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean entityExplosionRaysVisiblity;
	
	@Option(defaultValue = "300", 
			parserClass = IntegerParser.class)
	public static int entityExplosionRaysLifetime;
	
	@Option(defaultValue = "true", 
			parserClass = BooleanParser.class)
	public static boolean entityLogAutoArchiving;
	
	// TODO Assess the performance cost
	@Option(defaultValue = "2.0", 
			experimental = true, 
			parserClass = FloatParser.Positive.class)
	public static float getEntityRangeExpansion;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class, 
			globalOnly = true, 
			environment = EnvType.CLIENT)
	public static boolean hideSuvivalSaves;
	
	@Option(defaultValue = "TOP_RIGHT", 
			parserClass = AlignMode.Parser.class)
	public static AlignMode hudAlignMode;
	
	@Option(defaultValue = "(BL)^2/(mR)", 
			parserClass = StringParser.class)
	public static String hudStyles;
	
	@Option(defaultValue = "1.0", 
			parserClass = FloatParser.Positive.class, 
			experimental = true)
	public static float hudTextSize;
	
	@Option(defaultValue = "9", 
			parserClass = IntegerParser.HotbarLength.class)
	public static int hotbarLength;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean interactableB36;
	
	@Option(defaultValue = "-FOLLOW_SYSTEM_SETTINGS-", 
			parserClass = Language.Parser.class)
	public static String language;
	
	@Option(defaultValue = "10", 
			parserClass = IntegerParser.Positive.class)
	public static int maxClientTicksPerFrame;
	
	@Option(defaultValue = "180", 
			parserClass = FloatParser.Positive.class)
	public static float maxEndEyeTpRadius;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean mobFastKill;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean projectileChunkLoading;
	
	@Option(defaultValue = "3", 
			parserClass = IntegerParser.NonNegative.class)
	public static int projectileChunkLoadingRange;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean projectileChunkLoadingPermanence;
	
	@Option(defaultValue = "1.0", 
			parserClass = FloatParser.class)
	public static float projectileRandomnessScale;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean railNoAutoConnection;
	
	@Option(defaultValue = "[]", 
			parserClass = ListParser.Ticket.class)
	public static List<ChunkTicketType<?>> rejectChunkTicket;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean renderBlockShape;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean renderFluidShape;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean renderRedstoneGateInfo;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean serverSyncedBox;
	
	@Option(defaultValue = "-1", 
			parserClass = FloatParser.class)
	public static float serverSyncedBoxRenderRange;
	
	@Option(defaultValue = "[]", 
			parserClass = RangeParser.ChunkStatusRange.class)
	public static List<ChunkStatusSorter> skippedGenerationStages;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean skipUnloadedChunkInRaycasting;
	
	@Option(defaultValue = "true", 
			parserClass = BooleanParser.class)
	public static boolean stableHudLocation;
	
	// TODO
	@Option(defaultValue = "false", 
			experimental = true, 
			parserClass = BooleanParser.class)
	public static boolean strictAccessingPathParsing;
	
	@Option(defaultValue = "false", 
			experimental = true, 
			parserClass = BooleanParser.class)
	public static boolean superSuperSecretSetting;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean tntChunkLoading;
	
	@Option(defaultValue = "false", 
			parserClass = BooleanParser.class)
	public static boolean tntChunkLoadingPermanence;
	
	@Option(defaultValue = "3", 
			parserClass = IntegerParser.NonNegative.class)
	public static int tntChunkLoadingRange;
	
	@Option(defaultValue = "[]", 
			parserClass = ListParser.DebugRender.class)
	public static List<Field> vanillaDebugRenderers;
	
	private static void loadFromProperties(Properties options) {
		for(Field f : OPTIONS) {
			String serialized;
			String name = f.getName();
			Option o = f.getAnnotation(Option.class);
			try {
				@SuppressWarnings("deprecation")
				OptionParser<?> parser = o.parserClass().newInstance();
				try {
					options.computeIfAbsent(name, 
							(t) -> GLOBAL_OPTION_SERIALIZER.computeIfAbsent(name, (key) -> o.defaultValue()));
					serialized = (String) options.get(name);
					f.set(null, parser.tryParse(serialized));
					saveGlobal();
				} catch (InvaildOptionException e) {
					MessMod.LOGGER.warn("The value of option {} is invaild, restoring it to the default value: {}", name, e.getMessage());
					String dV;
					if(isVaild(parser, (String) GLOBAL_OPTION_SERIALIZER.get(name))) {
						dV = GLOBAL_OPTION_SERIALIZER.getProperty(name);
						options.put(name, dV);
					} else {
						dV = o.defaultValue();
						GLOBAL_OPTION_SERIALIZER.put(name, dV);
						options.put(name, dV);
					}

					serialized = dV;
					try {
						f.set(null, parser.tryParse(dV));
					} catch (IllegalArgumentException | IllegalAccessException | InvaildOptionException e1) {
						e1.printStackTrace();
					}
				}

				BiConsumer<String, @Nullable CommandContext<ServerCommandSource>> behavior = CUSTOM_APPLICATION_BEHAVIORS.get(f.getName());
				if(behavior != null) {
					behavior.accept(serialized, null);
				}
			} catch (SecurityException | IllegalAccessException | InstantiationException e1) {
				e1.printStackTrace();
				
			}
		}
		
		if(MessMod.LOGGER.isDebugEnabled()) {
			MessMod.LOGGER.debug("Loaded {} MessMod config from {}", OPTIONS.size(), localOptionFile.getAbsolutePath());
			OPTIONS.stream()
			.map((f) -> {
				return f.getName() + ": " + getString(f);
			})
			.forEach(MessMod.LOGGER::debug);
		}
	}
	
	public static void loadFromServer(InputStream in) throws IOException {
		Properties options = new Properties();
		options.load(in);
		loadFromProperties(options);
	}
	
	/**
	 * Refresh the save-local option storage
	 */
	public static synchronized void reload() {
		if(localOptionFile.exists()) {
			try (FileInputStream in = new FileInputStream(localOptionFile)) {
				localOptionSerializer.load(in);
			} catch (IOException e) {
				MessMod.LOGGER.fatal("Failed to open mcwmem.prop, the Minecraft may crash later.");
				e.printStackTrace();
			}
		} else {
			MessMod.LOGGER.info("Couldn't find mcwmem.prop, creating a new one.");
			writeDefault();
		}
		
		loadFromProperties(localOptionSerializer);
		sendOptionsToClientsIfNeeded();
	}

	// Only calls from MessMod.onInitialize() are permitted
	public static void loadGlobal() {
		if(GLOBAL_OPTION_FILE.exists()) {
			try (FileInputStream in = new FileInputStream(GLOBAL_OPTION_FILE)) {
				GLOBAL_OPTION_SERIALIZER.load(in);
			} catch (IOException e) {
				MessMod.LOGGER.fatal("Failed to open mcwmem.prop, the Minecraft may crash later.");
				e.printStackTrace();
			}
		} else {
			MessMod.LOGGER.info("Couldn't find mcwmem.prop, creating a new one.");
			writeDefault();
		}
		
		for(Field f : OPTIONS) {
			String name = f.getName();
			String serialized;
			Option o = f.getAnnotation(Option.class);
			try {
				@SuppressWarnings("deprecation")
				OptionParser<?> parser = o.parserClass().newInstance();
				try {
					GLOBAL_OPTION_SERIALIZER.computeIfAbsent(name, (k) -> o.defaultValue());
					serialized = (String) GLOBAL_OPTION_SERIALIZER.get(name);
					f.set(null, parser.tryParse(serialized));
					saveGlobal();
				} catch (InvaildOptionException e) {
					MessMod.LOGGER.warn("The value of option {} is invaild, restoring it to the default value: {}", name, e.getMessage());
					String dV = o.defaultValue();
					serialized = dV;
					GLOBAL_OPTION_SERIALIZER.put(name, dV);
					try {
						f.set(null, parser.tryParse(dV));
					} catch (IllegalArgumentException | IllegalAccessException | InvaildOptionException e1) {
						e1.printStackTrace();
					}
				}
				

				BiConsumer<String, @Nullable CommandContext<ServerCommandSource>> behavior = CUSTOM_APPLICATION_BEHAVIORS.get(f.getName());
				if(behavior != null) {
					behavior.accept(serialized, null);
				}
			} catch (SecurityException | IllegalAccessException | InstantiationException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private static boolean isVaild(OptionParser<?> parser, String val) {
		try {
			return (parser.tryParse(val) != null);
		} catch (InvaildOptionException e) {
			return false;
		}
	}
	
	private static void writeDefault() {
		OPTIONS.forEach((f) -> GLOBAL_OPTION_SERIALIZER.put(f.getName(), f.getAnnotation(Option.class).defaultValue()));
		saveGlobal();
	}

	private static void writeDefaultLocally() {
		OPTIONS.forEach((f) -> localOptionSerializer.put(f.getName(), 
				GLOBAL_OPTION_SERIALIZER.computeIfAbsent(f.getName(), (key) -> f.getAnnotation(Option.class).defaultValue())));
		save();
	}

	public static synchronized void save() {
		try (FileOutputStream in = new FileOutputStream(localOptionFile)) {
			localOptionSerializer.store(in, "MessMod Options");
		} catch (IOException e) {
			LogManager.getLogger().fatal("Failed to write mcwmem.prop!");
			e.printStackTrace();
		}
	}

	private static void saveGlobal() {
		try (FileOutputStream in = new FileOutputStream(GLOBAL_OPTION_FILE)) {
			GLOBAL_OPTION_SERIALIZER.store(in, "MessMod Options");
		} catch (IOException e) {
			LogManager.getLogger().fatal("Failed to write mcwmem.prop!");
			e.printStackTrace();
		}
	}

	public static void set(Field f, Object obj) {
		set(f, obj, null);
	}

	public static void set(Field f, Object obj, @Nullable CommandContext<ServerCommandSource> ct) {
		try {
			f.set(null, obj);
			@SuppressWarnings("deprecation")
			String serialized = f.getAnnotation(Option.class).parserClass()
					.newInstance().serializeObj(obj);
			localOptionSerializer.put(f.getName(), serialized);
			save();
			BiConsumer<String, @Nullable CommandContext<ServerCommandSource>> behavior = CUSTOM_APPLICATION_BEHAVIORS.get(f.getName());
			if(behavior != null) {
				behavior.accept(serialized, ct);
			}
			
			sendOptionsToClientsIfNeeded();
		} catch (IllegalArgumentException e) {
			MessMod.LOGGER.fatal("Couldn't set the value of option {}, that shouldn't happed!", f.getName());
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void setGlobal(Field f, Object obj) {
		try {
			GLOBAL_OPTION_SERIALIZER.put(f.getName(), f.getAnnotation(Option.class).parserClass()
					.newInstance().serializeObj(obj));
			saveGlobal();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		set(f, obj);
	}

	private static void sendOptionsToClientsIfNeeded() {
		if(MessMod.isDedicatedEnv() && MessMod.INSTANCE.getServerNetworkHandler() != null) {
			MessMod.INSTANCE.getServerNetworkHandler().sendToEveryone(toPacket());
		}
	}

	public static void sendOptionsTo(ServerPlayerEntity player) {
		player.networkHandler.sendPacket(toPacket());
	}
	
	private static CustomPayloadS2CPacket toPacket() {
		try {
			PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
			StringWriter sw = new StringWriter();
			localOptionSerializer.store(sw, "MessMod Options");
			buf.writeString(sw.toString());
			return new CustomPayloadS2CPacket(Channels.OPTIONS, buf);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getString(Field f) {
		return localOptionSerializer.get(f.getName()).toString();
	}
	
	public static String getGlobalString(Field f) {
		return GLOBAL_OPTION_SERIALIZER.get(f.getName()).toString();
	}

	private static void registerCustomApplicationBehavior(String name, 
			@Nullable BiConsumer<String, @Nullable CommandContext<ServerCommandSource>> behavior) {
		CUSTOM_APPLICATION_BEHAVIORS.put(name, behavior);
	}
	
	/**
	 * Update the 
	 * @param ms The current MinecraftServer, or null if closed.
	 */
	public static void updateServer(@Nullable MinecraftServer ms) {
		if(ms != null) {
			localOptionSerializer = new Properties();
			Path p = ms.getSavePath(WorldSavePathMixin.create("mcwmem.prop"));
			localOptionFile = p.toFile();
			if(!Files.exists(p)) {
				try {
					Files.createFile(p);
					writeDefaultLocally();
				} catch (IOException e) {
					throw new CrashException(new CrashReport("Failed to create config file for MessMod.", e));
				}
			}
			
			reload();
		} else {
			save();
			localOptionSerializer = null;
			localOptionFile = null;
		}
	}

	private static void sendErrorOrWarn(@Nullable CommandContext<ServerCommandSource> ct, String msg) {
		if (ct != null) {
			CommandUtil.error(ct, msg);
		} else {
			MessMod.LOGGER.warn(msg);
		}
	}

	public static boolean isSupportedInCurrentEnv(Option o) {
		EnvType currentEnv = FabricLoader.getInstance().getEnvironmentType();
		for(EnvType env : o.environment()) {
			if(env == currentEnv) {
				return true;
			}
		}
		
		return false;
	}

	public static String getDescription(String name) {
		return I18N.translate("opt." + name + ".desc");
	}

	@SuppressWarnings("deprecation")
	private static void loadDefaults() {
		OPTIONS.forEach((f) -> {
			Option o = f.getAnnotation(Option.class);
			try {
				f.set(null, o.parserClass().newInstance().tryParse(o.defaultValue()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
	}
	
	static{
		registerCustomApplicationBehavior("enabledTools", (val, ct) -> {
			if(ct == null) {
				return;
			}
			
			if(!FabricLoader.getInstance().isModLoaded("carpet")) {
				sendErrorOrWarn(ct, "Please install the carpet mod!");
			}
			
			try {
				if(new BooleanParser().tryParse(val)) {
					CommandUtil.execute(CommandUtil.noreplySourceFor(ct.getSource()), "/script load tool");
				}else {
					CommandUtil.execute(CommandUtil.noreplySourceFor(ct.getSource()), "/script unload tool");
				}
			} catch (InvaildOptionException e) {
				e.printStackTrace();
			}
		});
		BiConsumer<String, CommandContext<ServerCommandSource>> checkLithium = (val, ct) -> {
			if (FabricLoader.getInstance().isModLoaded("lithium")) {
				sendErrorOrWarn(ct, "Warning: This feature is not compatible with lithium. Maybe it won't work properly");
			}
		};
		registerCustomApplicationBehavior("entityExplosionInfluence", checkLithium);
		registerCustomApplicationBehavior("disableExplosionExposureCalculation", checkLithium);
		registerCustomApplicationBehavior("blockInfoRendererUpdateInFrozenTicks", (val, ct) -> {
			if (!FabricLoader.getInstance().isModLoaded("carpet")) {
				sendErrorOrWarn(ct, "Please install the carpet mod!");
			}
		});
		registerCustomApplicationBehavior("hudStyles", (val, ct) -> {
			if (!MessMod.isDedicatedServerEnv() && MessMod.INSTANCE.getClientHudManager() != null) {
				MessMod.INSTANCE.getClientHudManager().updateStyle(val);
			}
		});
		OPTIONS.forEach((o) -> {
			if(!I18N.EN_US.containsKey(String.format("opt.%s.desc", o.getName()))) {
				MessMod.LOGGER.warn("The description of option {} is missing!", o.getName());
			}
		});
		loadDefaults();
	}
}
