package pl.tomaszdziurko.guava.collect;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;
import pl.tomaszdziurko.guava.geo.Continent;
import pl.tomaszdziurko.guava.geo.Country;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Collections2 showcase
 */
public class Collections2Test {

    @Test
    public void shouldTransformCollection() throws Exception {

        //这里是将一个集合集合进行转换，返回另一个形式的集合罢了。

        // given
        ArrayList<Country> countries = Lists.newArrayList(Country.POLAND, Country.BELGIUM, Country.ENGLAND);

        // when
        Collection<String> capitalCities = Collections2.transform(countries,
                new Function<Country, String>() {

                    @Override
                    public String apply(@Nullable Country country) {
                        return country.getCapitalCity();
                    }
                });

        // then
        assertThat(capitalCities).containsOnly("Warsaw", "Brussels", "London");
    }

    @Test
    public void shouldFilterCountriesOnlyFromAfrica() throws Exception {

        //这里是将一个集合进行过滤，按照条件获取需要的数据。从另一个方面看，就是将一个集合转化为另一个集合。

        // given
        ArrayList<Country> countries = Lists.newArrayList(Country.POLAND, Country.BELGIUM, Country.SOUTH_AFRICA);

        // when
        Collection<Country> countriesFromAfrica = Collections2.filter(countries, new Predicate<Country>() {

            @Override
            public boolean apply(@Nullable Country country) {
                return Continent.AFRICA.equals(country.getContinent());
            }
        });

        // then
        assertThat(countriesFromAfrica).containsOnly(Country.SOUTH_AFRICA);
    }

    @Test
    public void shouldShowThatResultIsOnlyAView() throws Exception {

        //其实就是实现了过滤和重定向的功能，将一个集合重定向为另一个集合，当过滤条件非常复杂的时候，那么这个时候，他的优势就很突出了。

        // given
        ArrayList<Country> countries = Lists.newArrayList(Country.POLAND, Country.BELGIUM, Country.ENGLAND);

        // when
        Collection<String> capitalCities = Collections2.transform(countries,
                new Function<Country, String>() {

                    @Override
                    public String apply(@Nullable Country country) {
                        return country.getCapitalCity();
                    }
                });

        // then
        assertThat(capitalCities).containsOnly("Warsaw", "Brussels", "London");
        assertThat(capitalCities).excludes("Pretoria");

        countries.add(Country.SOUTH_AFRICA);

        assertThat(capitalCities).contains("Pretoria");
    }
}
