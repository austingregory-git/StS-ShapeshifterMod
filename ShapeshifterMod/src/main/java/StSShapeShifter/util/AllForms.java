package StSShapeShifter.util;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.BearForm;
import StSShapeShifter.cards.DeerForm;
import StSShapeShifter.cards.Lynx_Form;
import StSShapeShifter.cards.Tortoise_Form;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.stances.BearFormStance;
import StSShapeShifter.stances.DeerFormStance;
import StSShapeShifter.stances.LynxFormStance;
import StSShapeShifter.stances.TortoiseFormStance;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Arrays;

public class AllForms {
    public static ArrayList<String> getAllForms() {
        ArrayList<String> allForms = new ArrayList<String>(Arrays.asList(Tortoise_Form.ID, Lynx_Form.ID, BearForm.ID, DeerForm.ID));

        return allForms;
    }

    public static ArrayList<AbstractCard> getAllFormsCards() {
        ArrayList<AbstractCard> allForms = new ArrayList<AbstractCard>(Arrays.asList(new Tortoise_Form(), new Lynx_Form(), new BearForm(), new DeerForm()));

        return allForms;
    }

    public static ArrayList<String> getAllFormsStanceNames() {
        ArrayList<String> allForms = new ArrayList<String>(Arrays.asList(LynxFormStance.class.getSimpleName(), TortoiseFormStance.class.getSimpleName(), DeerFormStance.class.getSimpleName(), BearFormStance.class.getSimpleName()));

        return allForms;
    }



}
