package mage.cards.a;

import mage.abilities.Ability;
import mage.abilities.common.LegendarySpellAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.keyword.DiscoverEffect;
import mage.abilities.common.CanBeYourCommanderAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SuperType;
import mage.game.Game;
import mage.game.stack.Spell;

import java.util.UUID;

/**
 * @author Notshauna
 */

public final class AshayasEnduringBond extends CardImpl {

    public AshayasEnduringBond(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{1}{R}{G}");

        this.supertype.add(SuperType.LEGENDARY);

        // (You may cast a legendary sorcery only if you control a legendary creature or planeswalker.)
        this.addAbility(new LegendarySpellAbility());

        // Discover X, where X is the amount of mana spent to cast this spell.
        this.getSpellAbility().addEffect(new AshayasEnduringBondEffect());

        // Ashaya’s Enduring Bond can be your commander.
        this.addAbility(CanBeYourCommanderAbility.getInstance());

    }

    private AshayasEnduringBond(final AshayasEnduringBond card) {
        super(card);
    }

    @Override
    public AshayasEnduringBond copy() {
        return new AshayasEnduringBond(this);
    }

    class AshayasEnduringBondEffect extends OneShotEffect {

        AshayasEnduringBondEffect() {
            super(Outcome.PlayForFree);
            staticText = "Discover X, where X is the amount of mana spent to cast this spell.";
        }

        private AshayasEnduringBondEffect(final AshayasEnduringBondEffect effect) {
            super(effect);
        }

        @Override
        public AshayasEnduringBondEffect copy() {
            return new AshayasEnduringBondEffect(this);
        }

        @Override
        public boolean apply(Game game, Ability source) {
            Spell spell = game.getSpell(this.getTargetPointer().getFirst(game, source));
            if (spell != null) {
                new DiscoverEffect(spell.getManaValue()).apply (game, source);
                return true;
            }
            return false;
        }
    }

}