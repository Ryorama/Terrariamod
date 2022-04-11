package com.ryorama.terrariamod.core;

public interface ExtendedTexture {
	void setFilterSave(boolean bilinear, boolean mipmap);

	void restoreLastFilter();
}