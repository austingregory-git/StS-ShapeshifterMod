//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class VinesOfHonrogo extends AbstractRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(VinesOfHonrogo.class.getSimpleName());

    public VinesOfHonrogo() {
        super(ID, "sunflower.png", RelicTier.RARE, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return "At the start of each combat, apply 1 entangle to a random enemy.";
    }

    //going to need to make a monster entangle power
    public void atBattleStart() {
        AbstractMonster randomMonster = AbstractDungeon.getRandomMonster();
        this.addToBot(new ApplyPowerAction(randomMonster, AbstractDungeon.player, new EntanglePower(randomMonster)));
    }
    
    public AbstractRelic makeCopy() {
        return new VinesOfHonrogo();
    }

}




