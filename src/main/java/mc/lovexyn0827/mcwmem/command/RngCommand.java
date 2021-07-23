package mc.lovexyn0827.mcwmem.command;

import static net.minecraft.server.command.CommandManager.literal;

import java.util.Random;

import static net.minecraft.server.command.CommandManager.argument;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import mc.lovexyn0827.mcwmem.mixins.EntityAccessor;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;

public class RngCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		LiteralArgumentBuilder<ServerCommandSource> branchSetSeed = literal("setSeed").
				then(argument("seed",LongArgumentType.longArg()).
						executes((ct)->{
							getRandom(ct).setSeed(LongArgumentType.getLong(ct, "seed"));
							return 1;
						}));
		LiteralArgumentBuilder<ServerCommandSource> branchNext = literal("next").
				then(literal("int").
						executes((ct)->{
							CommandUtil.feedback(ct,getRandom(ct).nextInt());
							return 1;
						}).
						then(argument("bounds",IntegerArgumentType.integer(1)).
								executes((ct)->{
									CommandUtil.feedback(ct,getRandom(ct).nextInt(IntegerArgumentType.getInteger(ct, "bounds")));
									return 1;
								}))).
				then(literal("float").
						executes((ct)->{
							CommandUtil.feedback(ct,getRandom(ct).nextFloat());
							return 1;
						})).
				then(literal("double").
						executes((ct)->{
							CommandUtil.feedback(ct,getRandom(ct).nextDouble());
							return 1;
						})).
				then(literal("boolean").
						executes((ct)->{
							boolean bool = getRandom(ct).nextBoolean();
							CommandUtil.feedback(ct,bool);
							return bool?1:-1;
						})).
				then(literal("gaussian").
						executes((ct)->{
							CommandUtil.feedback(ct, getRandom(ct).nextGaussian());
							return 1;
						}));
		LiteralArgumentBuilder<ServerCommandSource> command = literal("rng").requires((source)->source.hasPermissionLevel(1)).
				then(literal("world").
						then(branchSetSeed).
						then(branchNext)).
				then(argument("target",EntityArgumentType.entity()).
						then(branchSetSeed).
						then(branchNext));
		dispatcher.register(command);
	}
	
	@SuppressWarnings("resource")
	private static Random getRandom(CommandContext<ServerCommandSource> ct) {
		if("/rng world".equals(ct.getInput().substring(0,10))) {
			return ct.getSource().getWorld().random;
		}else {
			try {
				Entity entity = EntityArgumentType.getEntity(ct, "target");
				return ((EntityAccessor)entity).getRamdomMCWMEM();
			} catch (CommandSyntaxException e) {
				CommandUtil.error(ct, e);
			}
		}
		return null;
	}
}
