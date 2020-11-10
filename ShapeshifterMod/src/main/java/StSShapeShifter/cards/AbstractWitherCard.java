package StSShapeShifter.cards;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.actions.ModifyMagicAction;
import StSShapeShifter.util.BloomCountUtils;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractWitherCard extends AbstractDefaultCard {

    // "How come DefaultCommonAttack extends CustomCard and not DynamicCard like all the rest?"

    // Well every card, at the end of the day, extends CustomCard.
    // Abstract Default Card extends CustomCard and builds up on it, adding a second magic number. Your card can extend it and
    // bam - you can have a second magic number in that card (Learn Java inheritance if you want to know how that works).
    // Abstract Dynamic Card builds up on Abstract Default Card even more and makes it so that you don't need to add
    // the NAME and the DESCRIPTION into your card - it'll get it automatically. Of course, this functionality could have easily
    // Been added to the default card rather than creating a new Dynamic one, but was done so to deliberately.

    public AbstractWitherCard(final String id,
                              final String img,
                              final int cost,
                              final CardType type,
                              final CardColor color,
                              final CardRarity rarity,
                              final CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);

    }

    public void applyGrow() {
        this.growValue = this.baseGrowValue;
        if(AbstractDungeon.player.hasPower(ShapeshifterMod.makeID("HummingbirdPower"))) {
            this.growValue += AbstractDungeon.player.getPower(ShapeshifterMod.makeID("HummingbirdPower")).amount;
        }
        if(AbstractDungeon.player.hasRelic(ShapeshifterMod.makeID("EmeraldPantherFigurine"))) {
            this.growValue += 1;
        }
    }

    public void applyWither() {
        this.witherValue = this.baseWitherValue;
    }

    public void simulateGrow(int times) {
        for(int i=0; i<times; i++) {
            this.addToBot(new ModifyDamageAction(this.uuid, this.growValue));
            this.addToBot(new ModifyBlockAction(this.uuid, this.growValue));
            this.addToBot(new ModifyMagicAction(this.uuid, this.growValue));
            updateBloomCount(this.growValue);
        }
    }

    public void updateBloomCount(int value) {
        BloomCountUtils.addBloomCount(value);
    }
}