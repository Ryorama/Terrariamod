package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {
	
	/*
	
	Random random;
	private int ticks = MinecraftClient.getInstance().getServer().getTicks();
	
	private int lastHealthValue;
	private int renderHealthValue;
	private long lastHealthCheckTime;
	private long heartJumpEndTick;
	
	public int scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();;
	public int scaledHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();;
	
	@Inject(at = @At("HEAD"), method = "renderStatusBars")
	private void renderStatusBars(MatrixStack matrices) {
		
		if (MinecraftClient.getInstance().player != null) {
	        boolean bl = this.heartJumpEndTick > (long)this.ticks && (this.heartJumpEndTick - (long)this.ticks) / 3L % 2L == 1L;
			this.scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
	         int j = this.renderHealthValue;
	         long l = Util.getMeasuringTimeMs();
	         int i = MathHelper.ceil(MinecraftClient.getInstance().player.getHealth());
	         this.random.setSeed((long)(this.ticks * 312871));
	         HungerManager hungerManager = MinecraftClient.getInstance().player.getHungerManager();
	         int k = hungerManager.getFoodLevel();
	         int m = this.scaledWidth / 2 - 91;
	         int n = this.scaledWidth / 2 + 91;
	         int o = this.scaledHeight - 39;
	         float f = (float)MinecraftClient.getInstance().player.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH);
	         int p = MathHelper.ceil(MinecraftClient.getInstance().player.getAbsorptionAmount());
	         int q = MathHelper.ceil((f + (float)p) / 2.0F / 10.0F);
	         int r = Math.max(10 - (q - 2), 3);
	         int s = o - (q - 1) * r - 10;
	         int t = o - 10;
	         int u = p;
	         int v = MinecraftClient.getInstance().player.getArmor();
	         int w = -1;
	         if (MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.REGENERATION)) {
	            w = this.ticks % MathHelper.ceil(f + 5.0F);
	         }
	         
	         if (l - this.lastHealthCheckTime > 1000L) {
	             this.lastHealthValue = i;
	             this.renderHealthValue = i;
	             this.lastHealthCheckTime = l;
	          }
			
			if (MinecraftClient.getInstance().player != null) {
		         int z;
		         int aa;
		         for(z = 0; z < 10; ++z) {
		            if (v > 0) {
		               aa = m + z * 8;
		               if (z * 2 + 1 < v) {
		                  this.drawTexture(matrices, aa, s, 34, 9, 9, 9);
		               }
	
		               if (z * 2 + 1 == v) {
		                  this.drawTexture(matrices, aa, s, 25, 9, 9, 9);
		               }
	
		               if (z * 2 + 1 > v) {
		                  this.drawTexture(matrices, aa, s, 16, 9, 9, 9);
		               }
		            }
		        }
				
				int ai;
		        int ad;
		        int ae;
		        for(z = MathHelper.ceil((f + (float)p) / 2.0F) - 1; z >= 0; --z) {
		        	aa = 16;
		            if (u <= 0) {
		               if (MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.POISON)) {
		                  aa += 36;
		               } else if (MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.WITHER)) {
		                  aa += 72;
		               } else if (MinecraftClient.getInstance().player.isFreezing()) {
		                  aa += 126;
		               }
		            }
	
		            int ab = 0;
		            if (bl) {
		               ab = 1;
		            }
	
		            ai = MathHelper.ceil((float)(z + 1) / 10.0F) - 1;
		            ad = m + z % 10 * 8;
		            ae = o - ai * r;
		            if (i <= 4) {
		               ae += this.random.nextInt(2);
		            }
	
		            if (u <= 0 && z == w) {
		               ae -= 2;
		            }
	
		            int af = 0;
		            if (MinecraftClient.getInstance().player.world.getLevelProperties().isHardcore()) {
		               af = 5;
		            }
		            
		            if (MinecraftClient.getInstance().player.getMaxHealth() <= 400) {
		            	RenderSystem.colorMask(red, green, blue, alpha);
		            }
	
		            this.drawTexture(matrices, ad, ae, 16 + ab * 9, 9 * af, 9, 9);
		            if (bl) {
		               if (z * 2 + 1 < j) {
		                  this.drawTexture(matrices, ad, ae, aa + 54, 9 * af, 9, 9);
		               }
	
		               if (z * 2 + 1 == j) {
		                  this.drawTexture(matrices, ad, ae, aa + 63, 9 * af, 9, 9);
		               }
		            }
	
		            if (u > 0) {
		               if (u == p && p % 2 == 1) {
		                  this.drawTexture(matrices, ad, ae, aa + 153, 9 * af, 9, 9);
		                  --u;
		               } else {
		                  this.drawTexture(matrices, ad, ae, aa + 144, 9 * af, 9, 9);
		                  u -= 2;
		               }
		            } else { 
		               if (z * 2 + 1 < i) {
		                  this.drawTexture(matrices, ad, ae, aa + 36, 9 * af, 9, 9);
		               }
	
		               if (z * 2 + 1 == i) {
		                  this.drawTexture(matrices, ad, ae, aa + 45, 9 * af, 9, 9);
		               }
		            }
		         }
			}
		}
	}
	*/
}