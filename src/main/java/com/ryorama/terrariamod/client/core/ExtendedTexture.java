package com.ryorama.terrariamod.client.core;

public interface ExtendedTexture {
	void setFilterSave(boolean bilinear, boolean mipmap);

	void restoreLastFilter();
}