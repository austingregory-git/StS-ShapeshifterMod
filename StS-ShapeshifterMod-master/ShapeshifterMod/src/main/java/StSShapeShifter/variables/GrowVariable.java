package StSShapeShifter.variables;

import StSShapeShifter.cards.AbstractDynamicCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static StSShapeShifter.ShapeshifterMod.makeID;

public class GrowVariable extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("Grow");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "theDefault:" (or, your id) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractDynamicCard) card).isGrowValueModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractDynamicCard) card).growValue;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractDynamicCard) card).baseGrowValue;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractDynamicCard) card).upgradedGrowValue;
    }
}
