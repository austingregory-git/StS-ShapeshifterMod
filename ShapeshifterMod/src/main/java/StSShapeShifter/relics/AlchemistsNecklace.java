//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class AlchemistsNecklace extends CustomRelic {
    public static final String ID = ShapeshifterMod.makeID("AlchemistsNecklace");
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(AlchemistsNecklace.class.getSimpleName() + PNG));

    public AlchemistsNecklace() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    public void onUsePotion() {
        this.addToBot(new DrawCardAction(2));
    }

    public AbstractRelic makeCopy() {
        return new AlchemistsNecklace();
    }

}





