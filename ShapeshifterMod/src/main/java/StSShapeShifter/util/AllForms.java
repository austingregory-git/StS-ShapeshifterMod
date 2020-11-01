package StSShapeShifter.util;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.*;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.*;
import StSShapeShifter.stances.*;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Arrays;

public class AllForms {
    public static ArrayList<String> getAllForms() {
        ArrayList<String> allForms = new ArrayList<String>(Arrays.asList(Tortoise_Form.ID, Lynx_Form.ID, BearForm.ID, DeerForm.ID, HydraForm.ID, DragonForm.ID,
                                                                    MammothForm.ID, RatForm.ID, EagleForm.ID, PhoenixForm.ID, FoxForm.ID, EntForm.ID,
                                                                    PorcupineForm.ID, SquirrelForm.ID, MonkeyForm.ID, LionForm.ID, OwlForm.ID, HummingbirdForm.ID,
                                                                    WaspSwarmForm.ID, MooseForm.ID, HippoForm.ID, RhinoCalfForm.ID, RaptorForm.ID, FlamingoForm.ID,
                                                                    PantherForm.ID, WolfForm.ID));

        return allForms;
    }

    public static ArrayList<AbstractCard> getAllFormsCards() {
        ArrayList<AbstractCard> allForms = new ArrayList<AbstractCard>(Arrays.asList(new Tortoise_Form(), new Lynx_Form(), new BearForm(), new DeerForm(),
                                                                          new DragonForm(), new HydraForm(), new MammothForm(), new RatForm(),
                                                                          new EagleForm(), new PhoenixForm(), new FoxForm(), new EntForm(),
                                                                          new PorcupineForm(), new SquirrelForm(), new MonkeyForm(), new LionForm(),
                                                                          new OwlForm(), new HummingbirdForm(), new WaspSwarmForm(), new MooseForm(),
                                                                          new HippoForm(), new RhinoCalfForm(), new RaptorForm(), new FlamingoForm(), new PantherForm(), new WolfForm()));

        return allForms;
    }

    public static ArrayList<String> getAllFormsStanceNames() {
        ArrayList<String> allForms = new ArrayList<String>(Arrays.asList(LynxFormStance.class.getSimpleName(), TortoiseFormStance.class.getSimpleName(), DeerFormStance.class.getSimpleName(), BearFormStance.class.getSimpleName()));

        return allForms;
    }

    public static ArrayList<String> getAllFormsPowerIDs() {
        ArrayList<String> allForms = new ArrayList<String>(Arrays.asList(TortoiseFormPower.POWER_ID, LynxFormPower.POWER_ID, BearFormPower.POWER_ID, DeerFormPower.POWER_ID, HydraFormPower.POWER_ID, DragonFormPower.POWER_ID,
                MammothFormPower.POWER_ID, RatFormPower.POWER_ID, EagleFormPower.POWER_ID, PhoenixFormPower.POWER_ID, FoxFormPower.POWER_ID, EntFormPower.POWER_ID,
                PorcupineFormPower.POWER_ID, SquirrelFormPower.POWER_ID, MonkeyFormPower.POWER_ID, LionFormPower.POWER_ID, OwlFormPower.POWER_ID, HummingbirdFormPower.POWER_ID,
                WaspSwarmFormPower.POWER_ID));

        return allForms;
    }
}
