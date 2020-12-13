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
import com.megacrit.cardcrawl.vfx.AwakenedEyeParticle;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;

public class RhinoFormStance extends AbstractStance {
    public static final String STANCE_ID = "RhinoFormStance";
    public static final String NAME = "Rhino Form Stance";
    private static final StanceStrings stanceString;
    private static long sfxId;
    private final AbstractPlayer owner;

    public RhinoFormStance() {
        this.ID = STANCE_ID;
        this.name = NAME;
        //this.name = null;
        this.updateDescription();
        this.owner = AbstractDungeon.player;
    }

    public void updateDescription() {
        //this.description = stanceString.DESCRIPTION[0];
        this.description = "Rhino Form Stance";
    }

    public void onExitStance() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ShapeshifterMod.makeID("RhinoFormPower")));
    }

    static {
        stanceString = CardCrawlGame.languagePack.getStanceString("RhinoFormStance");
        sfxId = -1L;
    }
}



