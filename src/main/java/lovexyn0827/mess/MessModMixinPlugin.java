package lovexyn0827.mess;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.function.BooleanSupplier;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import com.google.common.collect.ImmutableSet;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;

/**
 * Mainly used to determine if some mixins should be actually applied.
 * @author lovexyn0827
 * @date 2022/9/30
 */
public class MessModMixinPlugin implements IMixinConfigPlugin {
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String MESSMOD_MIXINS = "lovexyn0827.mess.mixins.";
	private static final File ADVANCED_MIXINS_CONFIGURATION = new File("advanced_mixins.prop");
	private static final Map<String, BooleanSupplier> CUSTOM_MINIX_REQUIREMENTS = new HashMap<>();
	private static final ImmutableSet<MixinInfo> ADVANCED_MIXINS;
	private static final ImmutableSet<String> ACTIVIATED_ADVANCED_MIXINS;
	
	@Override
	public void onLoad(String mixinPackage) {
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassNameFull) {
		String mixinClassName = mixinClassNameFull.replace(MESSMOD_MIXINS, "");
		if(ADVANCED_MIXINS.stream().anyMatch((m) -> m.name.equals(mixinClassName))
				&& !ACTIVIATED_ADVANCED_MIXINS.contains(mixinClassName)) {
			return false;
		}
		
		try {
			if(CUSTOM_MINIX_REQUIREMENTS.containsKey(mixinClassName)) {
				return CUSTOM_MINIX_REQUIREMENTS.get(mixinClassName).getAsBoolean();
			}
			
			return true;
		} catch (Exception e) {
			LOGGER.error("Failed to determine whether or not mixin {} should be applied!", mixinClassName);
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}
	
	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}
	
	private static BooleanSupplier isModLoaded(String id, @Nullable String minVer, @Nullable String maxVer) {
		Optional<ModContainer> mayMod = FabricLoader.getInstance().getModContainer(id);
		SemanticVersion minSemiVer;
		SemanticVersion maxSemiVer;
		try {
			if(minVer != null) {
				minSemiVer = SemanticVersion.parse(minVer);
			} else {
				minSemiVer = null;
			}
			
			if(maxVer != null) {
				maxSemiVer = SemanticVersion.parse(maxVer);
			} else {
				maxSemiVer = null;
			}
		} catch (VersionParsingException e) {
			throw new IllegalArgumentException();
		}
		
		return () -> {
			if(mayMod.isPresent()) {
				Version ver = mayMod.get().getMetadata().getVersion();
				return (maxSemiVer == null ? true : maxSemiVer.compareTo(ver) >= 0) 
						&& (minSemiVer == null ? true : minSemiVer.compareTo(ver) <= 0);
			} else {
				return true;
			}
		};
	}
	
	private static BooleanSupplier isModNotLoaded(String id, @Nullable String minVer, @Nullable String maxVer) {
		return () -> !isModLoaded(id, minVer, maxVer).getAsBoolean();
	}

	private static ImmutableSet<String> getActiviatedAdvancedMixins(ImmutableSet<MixinInfo> advancedMixins) {
		try {
			Properties config = new Properties();
			if(!ADVANCED_MIXINS_CONFIGURATION.exists() || "true".equals(System.clearProperty("messmod.chooseMixin"))) {
				ADVANCED_MIXINS_CONFIGURATION.createNewFile();
				try {
					MixinChoosingFrame frame = new MixinChoosingFrame();
					frame.setVisible(true);
					while(frame.choosing && frame.isVisible());
					frame.writeChoices(config);
				} catch (Throwable e) {
					LOGGER.error("Failed to display mixin choosing window!");
					e.printStackTrace();
					advancedMixins.forEach((entry) -> {
						config.put(entry.name, "false");
					});
				}
				
				try(FileWriter fw = new FileWriter(ADVANCED_MIXINS_CONFIGURATION)) {
					config.store(fw, "Advanced Mixins of MessMod");
				}
				
				return ImmutableSet.of();
			}
			
			ImmutableSet.Builder<String> builder = ImmutableSet.builder();
			try(FileReader fr = new FileReader(ADVANCED_MIXINS_CONFIGURATION)) {
				config.load(fr);
				config.forEach((k, v) -> {
					if(Boolean.parseBoolean((String) v)) {
						builder.add((String) k);
					}
				});
			}
			
			return builder.build();
		} catch (Exception e) {
			LOGGER.fatal("Failed to load activation config of advanced mixins!");
			e.printStackTrace();
			return ImmutableSet.of();
		}
	}
	
	static {
		CUSTOM_MINIX_REQUIREMENTS.put("StructureBlockBlockEntityMixin", isModNotLoaded("carpet", "1.4.25", null));
		ADVANCED_MIXINS = AdvancedMixinInfoBuilder.create()
				.add("ServerChunkManagerMainThreadExecutorMixin").addUsages("Chunk events").costly().risky()
				.build();
		ACTIVIATED_ADVANCED_MIXINS = getActiviatedAdvancedMixins(ADVANCED_MIXINS);
	}
	
	private static final class MixinChoosingFrame extends JFrame {
		private static final long serialVersionUID = 2370884454946517091L;
		private final Map<String, JCheckBox> mixins = new HashMap<>();
		protected volatile boolean choosing = true;
		
		protected MixinChoosingFrame() {
			this.setLayout(new GridLayout(0, 3, 3, 3));
			this.add(new JLabel("Name of Mixin"));
			this.add(new JLabel("Usage"));
			this.add(new JLabel("Impacts"));
			ADVANCED_MIXINS.forEach((s) -> {
				JCheckBox check = new JCheckBox(s.name);
				this.mixins.put(s.name, check);
				this.add(check);
				this.add(new JLabel(s.usages));
				this.add(new JLabel(s.impacts));
			});
			JButton doneBtn = new JButton("OK");
			doneBtn.addActionListener((ae) -> {
				this.choosing = false;
				this.dispose();
			});
			this.add(doneBtn);
			this.setSize(800, ADVANCED_MIXINS.size() * 32 + 64);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					MixinChoosingFrame.this.choosing = false;
					MixinChoosingFrame.this.dispose();
				}
			});
		}

		public void writeChoices(Properties config) {
			this.mixins.forEach((name, check) -> config.put(name, Boolean.toString(check.isSelected())));
		}
	}
	
	private static final class AdvancedMixinInfoBuilder {
		private List<MixinInfo> infoList = new ArrayList<>();
		private String currentMixin;
		private String usages;
		private String impacts;
		
		protected static AdvancedMixinInfoBuilder create() {
			return new AdvancedMixinInfoBuilder();
		}
		
		protected AdvancedMixinInfoBuilder add(String name) {
			if(this.currentMixin != null) {
				this.infoList.add(new MixinInfo(this.currentMixin, this.usages, this.impacts));
			}
			
			this.currentMixin = name;
			this.usages = "";
			this.impacts = "";
			return this;
		}
		
		protected AdvancedMixinInfoBuilder addUsages(String ... usages) {
			this.usages += ", " + String.join(", ", usages);
			return this;
		}
		
		protected AdvancedMixinInfoBuilder costly() {
			this.impacts += "High performance cost|";
			return this;
		}
		
		protected AdvancedMixinInfoBuilder risky() {
			this.impacts += "Poiential issues|";
			return this;
		}
		
		@SuppressWarnings("unused")
		protected AdvancedMixinInfoBuilder compatibility() {
			this.impacts += "Compatibility issues|";
			return this;
		}
		
		@SuppressWarnings("unused")
		protected AdvancedMixinInfoBuilder compatibility(String ... modids) {
			this.impacts += "Compatibility issues with " + String.join(", ", modids) + '|';
			return this;
		}
		
		@SuppressWarnings("unused")
		protected AdvancedMixinInfoBuilder experimantal(String ... modids) {
			this.impacts += "Experimantal|";
			return this;
		}
		
		protected ImmutableSet<MixinInfo> build() {
			if(this.currentMixin != null) {
				this.infoList.add(new MixinInfo(this.currentMixin, this.usages, this.impacts));
			}
			
			return ImmutableSet.copyOf(this.infoList);
		}
	}
	
	private static final class MixinInfo {
		public final String name;
		public final String usages;
		public final String impacts;
		
		public MixinInfo(String name, String usages, String impacts) {
			this.name = name;
			this.usages = usages;
			this.impacts = impacts;
		}
	}
}
