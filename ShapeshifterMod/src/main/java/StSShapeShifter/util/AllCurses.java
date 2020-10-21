package StSShapeShifter.util;

import StSShapeShifter.cards.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.*;

import java.util.ArrayList;
import java.util.Arrays;

public class AllCurses {
    public static ArrayList<String> getAllForms() {
        ArrayList<String> allCurses = new ArrayList<String>(Arrays.asList(Clumsy.ID, Decay.ID, Doubt.ID, Injury.ID, Normality.ID,
                                                                     Pain.ID, Parasite.ID, Pride.ID, Regret.ID, Shame.ID, Writhe.ID));

        return allCurses;
    }

    public static ArrayList<AbstractCard> getAllCursesCards() {
        ArrayList<AbstractCard> allCurses = new ArrayList<AbstractCard>(Arrays.asList(new Clumsy(), new Decay(), new Doubt(), new Injury(),
                                                                           new Normality(), new Pain(), new Parasite(), new Pride(),
                                                                           new Regret(), new Shame(), new Writhe()));
        return allCurses;
    }
}
