package org.cirneco.assertions.hamcrest.number;

import org.cirneco.assertions.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class IsBetweenTest extends BaseMatcherTest {

    public Matcher<Integer> isBetweenMatcher;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    public Comparable comparable;

    public String getMatcherSimpleName() {
        return IsBetween.class.getSimpleName();
    }

    @Before
    public void setup() {
        //Arrange
        final Integer from = 10;
        final Integer to = 12;
        isBetweenMatcher = IsBetween.between(from, to);
    }

    @Test
    public void testGivenThatFromIsNull_WhenCreateInstance_ThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetween.between(null, "");

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatToIsNull_WhenCreateInstance_ThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetween.between("", null);

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatFromNotBeforeTo_WhenCreateInstance_ThenIllegalArgumentExceptionIsThrown() {
        //Arrange
        thrown.expect(IllegalArgumentException.class);

        //Act
        IsBetween.between("Z", "A");

        //Assert
        fail("IllegalArgumentException expected but not thrown");
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<9> is not between <10> and <12>, both excluded",
                isBetweenMatcher, 9);
        assertHasMismatchDescription("<10> is not between <10> and <12>, both excluded",
                isBetweenMatcher, 10);
        assertHasMismatchDescription("<12> is not between <10> and <12>, both excluded",
                isBetweenMatcher, 12);
        assertHasMismatchDescription("<13> is not between <10> and <12>, both excluded",
                isBetweenMatcher, 13);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value between <10> and <12>, both excluded", isBetweenMatcher);
    }

    @Test
    public void testGivenNumberBetween_WhenBetween_ThenReturnTrue() throws Exception {
        //Act
        final boolean lowerBoundIsBetween = isBetweenMatcher.matches(11);

        //Assert
        assertThat(lowerBoundIsBetween, is(true));
    }

    @Test
    public void testGivenNumberEqualsToLowerBound_WhenBetween_ThenReturnFalse() throws Exception {
        //Act
        final boolean lowerBoundIsBetween = isBetweenMatcher.matches(10);

        //Assert
        assertThat(lowerBoundIsBetween, is(false));
    }

    @Test
    public void testGivenNumberEqualsToUpperBound_WhenBetween_ThenReturnFalse() throws Exception {
        //Act
        final boolean lowerBoundIsBetween = isBetweenMatcher.matches(12);

        //Assert
        assertThat(lowerBoundIsBetween, is(false));
    }

}