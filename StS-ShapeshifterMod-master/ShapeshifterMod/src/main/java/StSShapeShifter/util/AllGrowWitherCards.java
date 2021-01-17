package StSShapeShifter.util;

import StSShapeShifter.cards.*;
import StSShapeShifter.powers.*;
import StSShapeShifter.stances.*;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Arrays;

public class AllGrowWitherCards {
    public static ArrayList<AbstractCard> getAllGrowCards() {
        ArrayList<AbstractCard> allGrow = new ArrayList<AbstractCard>(Arrays.asList(new AdaptiveStrikes(), new BarkSkin(), new BladeOfGrass(), new BloomingSlash(),
                                                                          new BurrBomb(), new BurstingSpore(), new Efflorescence(), new FesteringBite(),
                                                                          new Mystical_Branch(), new MysticalSeed(), new PoisonIvy(), new RhinoCalfForm(), new RaptorForm(),
                                                                          new RockSlide(), new Summon_Mountains(), new TopazSkin(), new VileMushroom()));

        return allGrow;
    }

    public static ArrayList<AbstractCard> getAllWitherCards() {
        ArrayList<AbstractCard> allWither = new ArrayList<AbstractCard>(Arrays.asList(new Consume(), new CursedApple(), new Earthquake(), new FallingLeaves(),
                new FlowerShield(), new Maul(), new SunflowerShuriken(), new ThornedSkin(),
                new ThornToss(), new Uproot_Redwood(), new Wildfire2()));

        return allWither;
    }
}
