//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class WristwrapsOfDiscovery extends CustomRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(WristwrapsOfDiscovery.class.getSimpleName());
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(WristwrapsOfDiscovery.class.getSimpleName() + PNG));

    public WristwrapsOfDiscovery() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        if(this.counter == -2) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2)));
            this.counter = -1;
        }
    }

    public void onObtainCard(AbstractCard c) {
        this.counter = -2;
    }

    public AbstractRelic makeCopy() {
        return new WristwrapsOfDiscovery();
    }

}





