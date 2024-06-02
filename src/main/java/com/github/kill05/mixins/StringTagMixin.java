package com.github.kill05.mixins;

import com.mojang.nbt.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;

@Mixin(Tag.class)
public abstract class StringTagMixin<T> {

	@Shadow
	public abstract T getValue();

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Tag<?> tag)) return false;
		return Objects.equals(getValue(), tag.getValue());
	}
}
