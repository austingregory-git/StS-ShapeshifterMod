package StSShapeShifter.variables;

import StSShapeShifter.cards.AbstractDynamicCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import StSShapeShifter.cards.AbstractDefaultCard;

import static StSShapeShifter.ShapeshifterMod.makeID;

public class WitherVariable extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("Wither");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "theDefault:" (or, your id) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractDynamicCard) card).isWitherValueModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractDynamicCard) card).witherValue;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractDynamicCard) card).baseWitherValue;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractDynamicCard) card).upgradedWitherValue;
    }
}
