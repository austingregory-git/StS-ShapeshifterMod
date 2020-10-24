package StSShapeShifter.util;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.*;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.stances.*;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Arrays;

public class AllForms {
    public static ArrayList<String> getAllForms() {
        ArrayList<String> allForms = new ArrayList<String>(Arrays.asList(Tortoise_Form.ID, Lynx_Form.ID, BearForm.ID, DeerForm.ID, HydraForm.ID, DragonForm.ID, MammothForm.ID, RatForm.ID, EagleForm.ID, PhoenixForm.ID));

        return allForms;
    }

    public static ArrayList<AbstractCard> getAllFormsCards() {
        ArrayList<AbstractCard> allForms = new ArrayList<AbstractCard>(Arrays.asList(new Tortoise_Form(), new Lynx_Form(), new BearForm(), new DeerForm(), new DragonForm(), new HydraForm(), new MammothForm(), new RatForm(), new EagleForm(), new PhoenixForm()));

        return allForms;
    }

    public static ArrayList<String> getAllFormsStanceNames() {
        ArrayList<String> allForms = new ArrayList<String>(Arrays.asList(LynxFormStance.class.getSimpleName(), TortoiseFormStance.class.getSimpleName(), DeerFormStance.class.getSimpleName(), BearFormStance.class.getSimpleName()));

        return allForms;
    }
}
