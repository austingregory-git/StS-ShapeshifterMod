//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllForms;
import StSShapeShifter.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Collections;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class LoyalLobster extends CustomRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(LoyalLobster.class.getSimpleName());
    public int count;
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(LoyalLobster.class.getSimpleName() + PNG));

    public LoyalLobster() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onPlayerEndTurn() {
        ArrayList<AbstractCard> cardsThisTurn = new ArrayList<>(AbstractDungeon.actionManager.cardsPlayedThisTurn);
        for(AbstractCard c : cardsThisTurn) {
            if(AllForms.getAllForms().contains(c.cardID)) {
                count = 0;
                this.counter = 0;
            }
        }
        for(AbstractPower pid : AbstractDungeon.player.powers) {
            if(AllForms.getAllFormsPowerIDs().contains(pid.ID)) {
                count++;
                this.counter++;
            }
        }
        if(count == 3) {
            this.flash();
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 3)));
            this.count = 0;
            this.counter = 0;
            this.active = false;
        }
    }

    @Override
    public void atPreBattle() {
        this.count = 0;
        this.active = true;
    }

    public AbstractRelic makeCopy() {
        return new LoyalLobster();
    }

}





