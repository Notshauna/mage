package mage.cards.p;

import mage.MageInt;
import mage.abilities.triggers.BeginningOfEndStepTriggeredAbility;
import mage.abilities.condition.common.RevoltCondition;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.ExileThenReturnTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.AdventureCard;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.TargetController;
import mage.game.permanent.token.PegasusToken;
import mage.target.common.TargetControlledCreaturePermanent;
import mage.watchers.common.RevoltWatcher;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class PegasusGuardian extends AdventureCard {

    public PegasusGuardian(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, new CardType[]{CardType.INSTANT}, "{5}{W}", "Rescue the Foal", "{1}{W}");

        this.subtype.add(SubType.PEGASUS);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // At the beginning of your end step, if a permanent you controlled left the battlefield this turn, create a 1/1 white Pegasus creature token with flying.
        this.addAbility(new BeginningOfEndStepTriggeredAbility(
                TargetController.YOU, new CreateTokenEffect(new PegasusToken()),
                false, RevoltCondition.instance
        ).addHint(RevoltCondition.getHint()), new RevoltWatcher());

        // Rescue the Foal
        // Exile target creature you control, then return that card to the battlefield under its owner's control.
        this.getSpellCard().getSpellAbility().addEffect(new ExileThenReturnTargetEffect(false, true));
        this.getSpellCard().getSpellAbility().addTarget(new TargetControlledCreaturePermanent());

        this.finalizeAdventure();
    }

    private PegasusGuardian(final PegasusGuardian card) {
        super(card);
    }

    @Override
    public PegasusGuardian copy() {
        return new PegasusGuardian(this);
    }
}
