package ua.com.epam.utils.validator;

import com.google.common.collect.Ordering;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.CollectionUtils;
import org.json.JSONArray;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j
public class ArrayValidator {
    private RestClient client;
    private Gson g;

    public ArrayValidator(RestClient client) {
        this.client = client;
        this.g = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    }

    public ArrayValidator and() {
        return this;
    }

    public ArrayValidator isOrderedBy(String sortKey, String order) {
        String body = client.getResponse().getBody();
        List<String> valuesToCheck = getValuesByKey(sortKey, body);
        List<Long> longs;
        List<LocalDate> dates;

        if (valuesToCheck.size() == 0) {
            log.info("JSON Array is empty!");
            return this;
        }

        String val = valuesToCheck.get(0);
        try {
            Long.parseLong(val);
            longs = mapToLong(valuesToCheck);
            checkOrdering(longs, order);
        } catch (NumberFormatException e) {
            try {
                LocalDate.parse(val);
                dates = mapToDate(valuesToCheck);
                checkOrdering(dates, order);
            } catch (DateTimeParseException e1) {
                checkOrdering(valuesToCheck, order);
            }
        }
        return this;
    }

    public ArrayValidator sizeIs(int expectedArraySize) {
        String body = client.getResponse().getBody();
        JSONArray arr = new JSONArray(body);

        int actualSize = arr.length();
        if (actualSize != expectedArraySize) {
            log.error("!!! Invalid array size !!! Expected: " + expectedArraySize + ", but found " + actualSize);
            throw new AssertionError();
        }
        log.info("Array size is as expected!");

        return this;
    }

    public ArrayValidator isEmpty() {
        String body = client.getResponse().getBody();
        JSONArray arr = new JSONArray(body);

        int size = arr.length();
        if (size != 0) {
            log.error("!!! Array is not empty !!! Actual size is: " + size);
            throw new AssertionError();
        }

        return this;
    }

    public ArrayValidator sizeIsGreaterThan(int minArrSize) {
        String body = client.getResponse().getBody();
        JSONArray arr = new JSONArray(body);

        int actualSize = arr.length();
        if (minArrSize > actualSize) {
            log.error("!!! Array size is less !!! Expected min size: " + minArrSize + ", but found " + actualSize);
            throw new AssertionError();
        }
        log.info("Array size is greater than expected!");

        return this;
    }

    public ArrayValidator sizeIsLessThan(int maxArrSize) {
        String body = client.getResponse().getBody();
        JSONArray arr = new JSONArray(body);

        int actSize = arr.length();
        if (maxArrSize < actSize) {
            log.error("!!! Array size is greater !!! Expected max size: " + maxArrSize + ", but found " + actSize);
            throw new AssertionError();
        }
        log.info("Array size is less than expected!");

        return this;
    }

    public <T> ArrayValidator containsEntity(T objToVerify, Class<T> clazz) {
        String body = client.getResponse().getBody();
        JSONArray arr = new JSONArray(body);

        boolean condition = IntStream.range(0, arr.length())
                .mapToObj(arr::getJSONObject)
                .map(jObj -> g.fromJson(jObj.toString(), clazz))
                .collect(Collectors.toList())
                .contains(objToVerify);

        if (!condition) {
            log.error("!!! Response array didn't contain expected entity !!!");
            throw new AssertionError();
        }

        log.info("Response array contains expected entity");

        return this;
    }

    public <T> ArrayValidator equalsTo(List<T> expected, Class<T> clazz) {
        String body = client.getResponse().getBody();
        JSONArray arr = new JSONArray(body);

        List<T> actual = IntStream.range(0, arr.length())
                .mapToObj(arr::getJSONObject)
                .map(jObj -> g.fromJson(jObj.toString(), clazz))
                .collect(Collectors.toList());

        boolean condition = CollectionUtils.isEqualCollection(expected, actual);

        if (!condition) {
            log.error("!!! Response array didn't match the expected one !!!");
            throw new AssertionError();
        }

        log.info("Response array is equals to expected!");

        return this;
    }

    public <T> ArrayValidator isPaginated(int page, int countPerPage, List<T> all, Class<T> clazz) {
        String body = client.getResponse().getBody();
        JSONArray arr = new JSONArray(body);

        List<T> paginatedActual = IntStream.range(0, arr.length())
                .mapToObj(arr::getJSONObject)
                .map(jObj -> g.fromJson(jObj.toString(), clazz))
                .collect(Collectors.toList());

        List<T> paginatedExpected = all.stream()
                .skip((page - 1) * countPerPage)
                .limit(countPerPage)
                .collect(Collectors.toList());

        boolean condition = CollectionUtils.isEqualCollection(paginatedActual, paginatedExpected);

        if (!condition) {
            log.error("!!! Response array is not paginated as expected or objects are different !!!");
            throw new AssertionError();
        }

        log.info("Response array is paginated as expected!");

        return this;
    }

    private List<String> getValuesByKey(String key, String json) {
        return new JSONArray(json).toList()
                .stream()
                .map(o -> JsonPath.read(o, "$." + key).toString())
                .collect(Collectors.toList());
    }

    private List<Long> mapToLong(List<String> values) {
        return values.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    private List<LocalDate> mapToDate(List<String> values) {
        return values.stream()
                .map(LocalDate::parse)
                .collect(Collectors.toList());
    }

    private void checkOrdering(List values, String order) {
        if (order.equals("asc")) {
            if (!Ordering.natural().isOrdered(values)) {
                log.error("!!! Objects are not ordering in ascending order !!!");
                throw new AssertionError();
            }
            log.info("Ordered in ascending order!");
        } else {
            if (!Ordering.natural().reverse().isOrdered(values)) {
                log.error("!!! Objects are not ordering in descending order !!!");
                throw new AssertionError();
            }
            log.info("Ordered in descending order!");
        }
    }
}
