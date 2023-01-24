package lovexyn0827.mess.rendering;

import lovexyn0827.mess.MessMod;
import lovexyn0827.mess.options.OptionManager;
import lovexyn0827.mess.util.TickingPhase;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;

public class ChunkLoadingInfoRenderer {
	private static final ShapeSpace CHUNK_LOADING = new ShapeSpace("chunk_loading");
	private final TickingPhase.Event updateEvent;
	
	public ChunkLoadingInfoRenderer() {
		this.updateEvent = this::tick;
		TickingPhase.CHUNK.addEvent(updateEvent);
	}
	
	public void tick(TickingPhase phase, ServerWorld world) {
		ShapeSender ss = MessMod.INSTANCE.shapeSender;
		if(!OptionManager.chunkLoadingInfoRenderer || ss == null) {
			return;
		}
		
		RegistryKey<World> worldKey = world.getRegistryKey();
		if(worldKey == World.OVERWORLD) {
			ss.clearSpaceFromServer(CHUNK_LOADING);
		}
		
		int radius = OptionManager.chunkLoadingInfoRenderRadius;
		int diameter = 2 * radius + 1;
		for(ServerPlayerEntity player : world.getPlayers()) {
			if(player.isHolding(Items.NAUTILUS_SHELL)) {
				ServerChunkManager scm = world.getChunkManager();
				int x = player.chunkX - radius;
				int z = player.chunkZ - radius;
				for(int dx = 0; dx < diameter; dx++) {
					for(int dz = 0; dz < diameter; dz++) {
						ChunkPos pos = new ChunkPos(x + dx, z + dz);
						int blockX = pos.getStartX() + 7;
						int blockZ = pos.getStartZ() + 7;
						WorldChunk chunk = scm.getWorldChunk(x + dx, z + dz, false);
						double height;
						if(chunk != null) {
							height = chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE)
									.get(8, 8);
						} else {
							height = player.getY();
						}
						
						int color;
						if(scm.shouldTickChunk(pos)) {
							// Entity processing
							color = 0xFF0000BF;
						} else if(scm.shouldTickBlock(pos.getStartPos())){
							// Lazy loaded
							color = 0x00FF00BF;
						} else {
							// Not ticking
							color = 0xBFBFBFBF;
						}
						
						RenderedBox marker = new RenderedBox(blockX, height, blockZ, 
								blockX + 2.0D, height + 0.5D, blockZ + 2.0D, 0x000000FF, color, 
								2, world.getTime());
						ss.addShape(marker, worldKey, CHUNK_LOADING);
					}
				}
			}
		}
	}
	
	public void close() {
		TickingPhase.CHUNK.addEvent(this.updateEvent);
	}
}