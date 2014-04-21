package erebus.world.biomes.decorators;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.world.biomes.decorators.type.FeatureType;
import erebus.world.biomes.decorators.type.OreSettings;
import erebus.world.biomes.decorators.type.OreSettings.OreType;
import erebus.world.feature.plant.WorldGenGiantFlowers;
import erebus.world.feature.tree.WorldGenCypressTree;

public class BiomeDecoratorElysianFields extends BiomeDecoratorBaseErebus{
	private final WorldGenerator genTreeCypress = new WorldGenCypressTree();
	private final WorldGenerator genGiantFlowers = new WorldGenGiantFlowers();
	
	@Override
	public void decorate(){
		for(int attempt = 0, xx, yy, zz; attempt < 200; attempt++){
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			yy = 20 + rand.nextInt(80);

			if (world.getBlockId(xx,yy - 1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
				genTreeCypress.generate(world,rand,xx,yy,zz);
			}
		}

		if (rand.nextBoolean()){
			for(int attempt = 0, xx, yy, zz; attempt < 65; attempt++){
				xx = x + offsetXZ();
				yy = 15 + rand.nextInt(90);
				zz = z + offsetXZ();

				if (world.getBlockId(xx,yy - 1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
					genGiantFlowers.generate(world,rand,xx,yy,zz);
				}
			}
		}
	}
	
	@Override
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres){
		switch(oreType){
			case COAL: oreGen.setIterations(extraOres?2:3,extraOres?3:4).setY(5,48); break; // ~2.5 times smaller area, thus less iterations
			case IRON: oreGen.setChance(0.75F).setIterations(extraOres?2:3,extraOres?4:5).setY(5,42); break; // ~3 times smaller area, thus lower chance and iterations
			case GOLD: oreGen.setIterations(extraOres?2:3); break; // 2 veins less
			case EMERALD: oreGen.setIterations(2,4).setCheckArea(2); break; // 2 veins more
			case JADE: oreGen.setIterations(2,5); break; // 1 vein more
			case PETRIFIED_WOOD: oreGen.setChance(0F); break;
			case FOSSIL: oreGen.setChance(0.25F).setOreAmount(5,8); break; // double chance, lower amount per vein
		}
	}

	@Override
	public void generateFeature(FeatureType featureType){
		if (featureType == FeatureType.REDGEM){
			for(attempt = 0; attempt < 2+rand.nextInt(2); attempt++){
				genRedGem.generate(world,rand,x+offsetXZ(),64+rand.nextInt(60),z+offsetXZ());
			}
		}
		else super.generateFeature(featureType);
	}
}