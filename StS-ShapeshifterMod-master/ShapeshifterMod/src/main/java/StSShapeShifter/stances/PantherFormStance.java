package StSShapeShifter.stances;

import StSShapeShifter.ShapeshifterMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;

public class PantherFormStance extends AbstractStance {
    public static final String STANCE_ID = "PantherFormStance";
    private static final StanceStrings stanceString;
    private static long sfxId;
    private final AbstractPlayer owner;

    public PantherFormStance() {
        this.ID = STANCE_ID;
        this.name = STANCE_ID;
        //this.name = null;
        this.updateDescription();
        this.owner = AbstractDungeon.player;
    }

    public void updateDescription() {
        //this.description = stanceString.DESCRIPTION[0];
        this.description = "Panther Form Stance";
    }

    /*public void onEnterStance() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                new DexterityPower(owner, amount), amount));
    }*/

    public void updateAnimation() {
/*        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.04F;
                AbstractDungeon.effectsQueue.add(new CalmParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Calm"));
        }*/

    }
    public void onExitStance() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ShapeshifterMod.makeID("PantherFormPower")));
    }

    static {
        stanceString = CardCrawlGame.languagePack.getStanceString("Panther Form Stance");
        sfxId = -1L;
    }
}





