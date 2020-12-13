package StSShapeShifter.util;

import StSShapeShifter.cards.*;
import StSShapeShifter.cards.tempCards.*;
import StSShapeShifter.stances.BearFormStance;
import StSShapeShifter.stances.DeerFormStance;
import StSShapeShifter.stances.LynxFormStance;
import StSShapeShifter.stances.TortoiseFormStance;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Arrays;

public class AllFruit {
    public static ArrayList<String> getAllFruit() {
        return new ArrayList<String>(Arrays.asList(Tortoise_Form.ID, Lynx_Form.ID, BearForm.ID, DeerForm.ID, HydraForm.ID, DragonForm.ID, MammothForm.ID, RatForm.ID, EagleForm.ID, PhoenixForm.ID));
    }

    public static ArrayList<AbstractCard> getAllFruitCards() {
        return new ArrayList<AbstractCard>(Arrays.asList(new Banana(), new Pineapple(), new Dragonfruit(), new Cherry(), new Avocado()));

    }
}
