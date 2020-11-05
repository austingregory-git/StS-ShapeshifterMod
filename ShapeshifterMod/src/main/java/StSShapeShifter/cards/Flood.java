package StSShapeShifter.cards;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.ShapeShifter;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class Flood extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(Flood.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;
    //public AbstractPlayer owner = null;
    //public int amount = Integer.MIN_VALUE;
    private static final int COST = 2;
    private static final int MAGIC = 2;

    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public Flood() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var3.hasNext()) {
                AbstractMonster monster = (AbstractMonster)var3.next();
                if (!monster.isDead && !monster.isDying && monster.hasPower("Minion")) {
                    this.addToBot(new InstantKillAction(m));
                }
                else if(!monster.isDead && !monster.isDying) {
                    this.addToBot(new ApplyPowerAction(monster, p, new VulnerablePower(monster, this.magicNumber, false), this.magicNumber));
                    this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, this.magicNumber, false), this.magicNumber));
                }
            }
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = "Innate. Destroy all minions. Apply !M! Vulnerable and Weak to all remaining enemies. Exhaust.";
            initializeDescription();
        }
    }
}



