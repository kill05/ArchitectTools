package com.github.kill05.materials;

import com.github.kill05.ArchitectTools;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;

public enum MaterialType {
	NORMAL(""),
	CONTRAST("_contrast"),
	BONE("_bone"),
	CACTUS("_cactus"),
	PAPER("_paper");

	private final String textureName;

	MaterialType(String textureName) {
		this.textureName = textureName;
	}

	public String getTextureName() {
		return textureName;
	}

	public static IconCoordinate getTexture(String texture, ArchitectMaterial material) {
		String end = (material != null ? material.type().getTextureName() : "");
		return TextureRegistry.getTexture(ArchitectTools.MOD_ID + ":item/" + texture + end);
	}
}
