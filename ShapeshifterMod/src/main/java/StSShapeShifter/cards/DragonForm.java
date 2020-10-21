package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.DeerFormPower;
import StSShapeShifter.powers.DragonFormPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class DragonForm extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(DragonForm.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;
    //public AbstractPlayer owner = null;
    //public int amount = Integer.MIN_VALUE;
    private static final int COST = 4;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DAMAGE = 4;
    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public DragonForm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.isMultiDamage = true;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.defaultBaseSecondMagicNumber = 6;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber;
        this.exhaust = true;

        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var3.hasNext()) {
                AbstractMonster monster = (AbstractMonster)var3.next();
                if (!monster.isDead && !monster.isDying) {
                    this.addToBot(new ApplyPowerAction(monster, p, new VulnerablePower(monster, this.magicNumber, false), this.magicNumber));
                    this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, this.magicNumber, false), this.magicNumber));
                    //this.addToBot(new DamageAction(monster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                }
            }
        }

        if(!p.stance.ID.equals("DragonFormStance") && !p.hasPower("CannotChangeStancePower")) {
            this.addToBot(new ApplyPowerAction(p, p, new DragonFormPower(p, this.defaultSecondMagicNumber)));
            this.addToBot(new ChangeStanceAction("DragonFormStance"));
        }

        //this.addToBot();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgradeMagicNumber(1);
            upgradeDefaultSecondMagicNumber(2);
            initializeDescription();
        }
    }
}



