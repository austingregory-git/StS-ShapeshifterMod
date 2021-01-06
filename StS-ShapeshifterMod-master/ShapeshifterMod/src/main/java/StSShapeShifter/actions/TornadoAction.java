package StSShapeShifter.actions;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import java.util.ArrayList;
import java.util.UUID;

public class TornadoAction extends AbstractGameAction {
    private UUID uuid;
    public AbstractCard card;
    public int count = 0;

    public TornadoAction() {
    }

    public void update() {
        CardGroup tmp;
        AbstractDungeon.player.discardPile.shuffle();
        tmp = AbstractDungeon.player.drawPile;
        AbstractDungeon.player.drawPile = AbstractDungeon.player.discardPile;
        AbstractDungeon.player.discardPile = tmp;
        AbstractDungeon.player.hand.refreshHandLayout();
        this.addToBot(new SFXAction("ATTACK_WHIRLWIND"));
        this.addToBot(new VFXAction(new WhirlwindEffect(), 0.0F));

        this.isDone = true;
    }
}

