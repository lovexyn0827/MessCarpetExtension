package lovexyn0827.mess.command;

import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;

import lovexyn0827.mess.command.EnumSetArgumentType.Empty;
import lovexyn0827.mess.mixins.ArgumentTypesMixin;
import lovexyn0827.mess.options.OptionManager;
import lovexyn0827.mess.util.Reflection;
import lovexyn0827.mess.util.access.AccessingPathArgumentType;
import lovexyn0827.mess.util.i18n.I18N;
import lovexyn0827.mess.util.phase.TickingPhaseArgumentType;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

public class CommandUtil {
	public static final Predicate<ServerCommandSource> COMMAND_REQUMENT = (s) -> 
			!OptionManager.commandExecutionRequirment || s.hasPermissionLevel(1);
	public static final SuggestionProvider<ServerCommandSource> ENTITY_TYPES = (ct, b) -> {
		Registries.ENTITY_TYPE.getIds().stream().map(Identifier::getPath).forEach(b::suggest);
		return b.buildFuture();
	};
	public static final SuggestionProvider<ServerCommandSource> FIELDS_SUGGESTION = (ct, builder) -> {
		Identifier id = new Identifier(StringArgumentType.getString(ct, "entityType"));
		EntityType<?> type = Registries.ENTITY_TYPE.get(id);
		Class<?> clazz = Reflection.ENTITY_TYPE_TO_CLASS.get(type);
		Reflection.getAvailableFields(clazz).forEach(builder::suggest);
		builder.suggest("-THIS-");
		return builder.buildFuture();
	};
	private static CommandOutput noreplyOutput;
	private static ServerCommandSource noreplySource;
	private static ServerCommandSource noreplyPlayerSource;
	private static CommandManager commandManager;
	private static boolean firstPlayerJoined = false;
	private static MinecraftServer server;
	
	public static void updateServer(MinecraftServer serverIn) {
		if(serverIn == null) {
			noreplySource = null;
			noreplyPlayerSource = null;
			commandManager = null;
			firstPlayerJoined = false;
			SetExplosionBlockCommand.reset();
			LogPacketCommand.reset();
			LazyLoadCommand.reset();
		} else {
			 noreplyOutput = new CommandOutput(){
				public boolean shouldReceiveFeedback() {
					return false;
				}

				public boolean shouldTrackOutput() {
					return false;
				}

				public boolean shouldBroadcastConsoleToOps() {
					return false;
				}

				@Override
				public void sendMessage(Text var1) {
				}
				
			};
			noreplySource = new ServerCommandSource(noreplyOutput,
					Vec3d.ZERO, 
					Vec2f.ZERO, 
					serverIn.getOverworld(), 
					4, 
					"NOREPLY", 
					Text.literal("NOREPLY"), 
					serverIn, 
					null);
			commandManager = serverIn.getCommandManager();
			server = serverIn;
		}
	}
	
	public static void feedback(CommandContext<? extends ServerCommandSource> ct, Object ob) {
		ct.getSource().sendFeedback(Text.literal(ob != null ? I18N.translate(ob.toString()) : "[null]"), false);
	}
	
	public static void feedbackWithArgs(CommandContext<? extends ServerCommandSource> ct, String fmt, Object ... args) {
		ct.getSource().sendFeedback(Text.literal(String.format(I18N.translate(fmt), args)), false);
	}
	
	public static void feedbackRaw(CommandContext<? extends ServerCommandSource> ct, Object ob) {
		ct.getSource().sendFeedback(Text.literal(ob == null ? "[null]" : ob.toString()), false);
	}
	
	public static void error(CommandContext<? extends ServerCommandSource> ct, Object ob) {
		ct.getSource().sendError(Text.literal(I18N.translate(ob.toString())));
		if(OptionManager.superSuperSecretSetting) {
			Thread.dumpStack();
		}
	}
	
	public static void errorWithArgs(CommandContext<? extends ServerCommandSource> ct, String fmt, Object ... args) {
		ct.getSource().sendError(Text.literal(String.format(I18N.translate(fmt), args)));
		if(OptionManager.superSuperSecretSetting) {
			Thread.dumpStack();
		}
	}

	public static void error(CommandContext<ServerCommandSource> ct, String string, Exception e) {
		String details = e.toString() + '\n' + e.getStackTrace()[0];
		ct.getSource().sendError(Text.literal(I18N.translate(string) + ": " + I18N.translate(e.getMessage()))
				.styled((s) -> s.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal(details)))));
		if(OptionManager.superSuperSecretSetting) {
			e.printStackTrace();
			Thread.dumpStack();
		}
	}
	
	public static void errorRaw(CommandContext<ServerCommandSource> ct, String str, @NotNull Exception e) {
		String details = e.toString() + '\n' + e.getStackTrace()[0];
		ct.getSource().sendError(Text.literal(str == null ? "[null]" : str)
				.styled((s) -> s.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal(details)))));
		if(OptionManager.superSuperSecretSetting) {
			e.printStackTrace();
			Thread.dumpStack();
		}
	}

	public static ServerCommandSource noreplySource() {
		if(noreplySource == null) throw new RuntimeException("Called before initialzation");
		return noreplySource;
	}

	public static int execute(ServerCommandSource source, String command) {
		return commandManager.executeWithPrefix(source, command);
	}

	public static ServerCommandSource noreplyPlayerSources() {
		if(!firstPlayerJoined) throw new RuntimeException("Called before initialzation");
		return noreplyPlayerSource;
	}
	
	public static void tryUpdatePlayer(ServerPlayerEntity player) {
		if(firstPlayerJoined) return;
		noreplyPlayerSource = new ServerCommandSource(noreplyOutput,
				player.getPos(), 
				player.getRotationClient(), 
				server.getOverworld(), 
				4, 
				"NOREPLY", 
				Text.literal("NOREPLY"), 
				server, 
				player);
		firstPlayerJoined = true;
	}

	public static ServerCommandSource noreplySourceFor(ServerCommandSource source) {
		Entity entity = source.getEntity();
		return new ServerCommandSource(noreplyOutput,
				source.getPosition(), 
				source.getRotation(), 
				source.getWorld(), 
				4, 
				"NOREPLY", 
				Text.literal("NOREPLY"), 
				server, 
				entity);
	}
	
	public static SuggestionProvider<ServerCommandSource> immutableSuggestions(Object ... args) {
		return (ct, builder) -> {
			Stream.of(args)
					.map(Object::toString)
					.forEach(builder::suggest);
			return builder.buildFuture();
		};
	}
	
	public static SuggestionProvider<ServerCommandSource> immutableSuggestionsOfEnum(Class<? extends Enum<?>> class1) {
		return (ct, builder) -> {
			Stream.of(class1.getEnumConstants())
					.map(Enum::name)
					.forEach(builder::suggest);
			return builder.buildFuture();
		};
	}

	public static void registerArgumentTypes() {
		ArgumentTypesMixin.registerForMessMod(Registries.COMMAND_ARGUMENT_TYPE, "mess_enum_set", 
				EnumSetArgumentType.class, 
				ConstantArgumentSerializer.of(() -> EnumSetArgumentType.of(Empty.class)));
		ArgumentTypesMixin.registerForMessMod(Registries.COMMAND_ARGUMENT_TYPE, "mess_filtered_set", 
				FilteredSetArgumentType.class, 
				ConstantArgumentSerializer.of(() -> FilteredSetArgumentType.of(new HashMap<>())));
		ArgumentTypesMixin.registerForMessMod(Registries.COMMAND_ARGUMENT_TYPE, "mess_float", 
				ExtendedFloatArgumentType.class, 
				ConstantArgumentSerializer.of(ExtendedFloatArgumentType::floatArg));
		ArgumentTypesMixin.registerForMessMod(Registries.COMMAND_ARGUMENT_TYPE, "mess_accessing_path", 
				AccessingPathArgumentType.class, 
				ConstantArgumentSerializer.of(AccessingPathArgumentType::accessingPathArg));
		ArgumentTypesMixin.registerForMessMod(Registries.COMMAND_ARGUMENT_TYPE, "mess_phase", 
				TickingPhaseArgumentType.class, 
				ConstantArgumentSerializer.of(TickingPhaseArgumentType::phaseArg));
	}
}
