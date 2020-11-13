package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.AllMonsterPowers;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.*;

import java.util.ArrayList;
import java.util.Random;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class Absorb extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(Absorb.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 1;
    private static final int UPGRADE_BASE_COST = 0;

    private int count = 0;

    // /STAT DECLARATION/


    public Absorb() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 2;
        this.defaultBaseSecondMagicNumber = 1;
        this.exhaust = true;


        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    //TODO Add player copies of monster powers, add cant use
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractPower> monsterPowers = m.powers;
        ArrayList<AbstractPower> monsterBuffs = new ArrayList<>();
        if(monsterPowers.size() > 0) {
            for(AbstractPower power : monsterPowers) {
                if(power != null && power.type == AbstractPower.PowerType.BUFF && AllMonsterPowers.getAllViableMonsterPowers().contains(power.ID))
                    monsterBuffs.add(power);
            }
            if(monsterBuffs.size() < 1) {
                this.cantUseMessage = "The enemy has no viable buffs to copy.";
            } else {
                AbstractPower stolenPower = monsterBuffs.get(new Random().nextInt(monsterBuffs.size()));
                ShapeshifterMod.logger.info(stolenPower.ID);
                ApplyMonsterPower(stolenPower, p);
                //this.addToBot(new ApplyPowerAction(p, p, stolenPower));
            }
        }


    }

    public void ApplyMonsterPower(AbstractPower power, AbstractPlayer p) {
        switch(power.ID) {
            case "Strength":
                this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, power.amount), power.amount));
                break;
            case "Ritual":
                this.addToBot(new ApplyPowerAction(p, p, new RitualPower(p, power.amount, true)));
                break;
            case "Curl Up":
                //this.addToBot(new ApplyPowerAction(p, p, new CurlUpPowerPlayer(p, power.amount)));
                break;
            case "Angry":
                this.addToBot(new ApplyPowerAction(p, p, new AngryPower(p, power.amount)));
                break;
            case "Intangible":
                this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, power.amount)));
                break;
            case "Metallicize":
                this.addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, power.amount)));
                break;
            case "Thorns":
                this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, power.amount)));
                break;
            case "Explosive":
                //this.addToBot(new ApplyPowerAction(p, p, new ExplosivePowerPlayer(p, power.amount)));
                break;
            case "Malleable":
                this.addToBot(new ApplyPowerAction(p, p, new MalleablePower(p, power.amount)));
                break;
            case "Spore Cloud":
                //this.addToBot(new ApplyPowerAction(p, p, new SporeCloudPowerPlayer(p, power.amount)));
                break;
            case "Generic Strength Up Power":
                this.addToBot(new ApplyPowerAction(p, p, new GenericStrengthUpPower(p, "Strength Up", power.amount)));
                break;
            case "Artifact":
                this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, power.amount)));
        }
    }
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_BASE_COST);
            initializeDescription();
        }
    }
}




