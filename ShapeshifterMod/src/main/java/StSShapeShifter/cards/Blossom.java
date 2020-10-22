package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import com.evacipated.cardcrawl.mod.stslib.actions.common.ModifyExhaustiveAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.TheDefault;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;
import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class Blossom extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(Blossom.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 3;
    private static final int UPGRADE_BASE_COST = 2;

    private int count = 0;

    // /STAT DECLARATION/


    public Blossom() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 2;
        this.defaultBaseSecondMagicNumber = 1;
        this.exhaust = true;


        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractPower> playerPowers = p.powers;
        Iterator var3 = playerPowers.iterator();

        while(var3.hasNext()) {
            AbstractPower power = (AbstractPower)var3.next();
            if (power != null && power.type == AbstractPower.PowerType.BUFF) {
                this.addToBot(new ApplyPowerAction(p, p, power));
                if(this.upgraded)
                    this.addToBot(new ApplyPowerAction(p, p, power));
            }
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
            this.rawDescription = "Triple your current buffs";
        }
    }
}


