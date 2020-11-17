//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllForms;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Fertilizer extends AbstractRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(Fertilizer.class.getSimpleName());
    public HashMap<UUID, Integer> cardsPlayed = new HashMap<>();

    public Fertilizer() {
        super(ID, "sunflower.png", RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(cardsPlayed.containsKey(targetCard.uuid) && cardsPlayed.get(targetCard.uuid) == 3) {
            this.flash();
            this.addToBot(new GainEnergyAction(3));
            this.addToBot(new DrawCardAction(3));
            this.active = false;
        }
        else if (!cardsPlayed.containsKey(targetCard.uuid)) {
            cardsPlayed.put(targetCard.uuid, 1);
            this.flash();
            this.counter = 1;
        }
        else {
            cardsPlayed.put(targetCard.uuid, cardsPlayed.get(targetCard.uuid) + 1);
            this.flash();
            this.counter = cardsPlayed.get(targetCard.uuid);
        }
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
        cardsPlayed.clear();
        this.active = true;
    }

    public AbstractRelic makeCopy() {
        return new Fertilizer();
    }

}





