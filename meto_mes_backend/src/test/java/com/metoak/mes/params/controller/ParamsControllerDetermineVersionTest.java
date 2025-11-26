package com.metoak.mes.params.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.service.IMoParamsDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParamsControllerDetermineVersionTest {

    @Autowired
    private IMoParamsDetailService moParamsDetailService;
    private Method determineVersionMethod;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception {
        determineVersionMethod = moParamsDetailService.getClass()
                .getDeclaredMethod("determineVersion", List.class, JsonNode.class);
        determineVersionMethod.setAccessible(true);
    }

    @Test
    void shouldStartAtFirstVersionWhenNoHistory() throws Exception {
        VersionResultView result = invokeDetermineVersion(new ArrayList<>(), jsonNode("{\"a\":1}"));

        assertThat(result.major).isEqualTo(1);
        assertThat(result.minor).isEqualTo(0);
        assertThat(result.patch).isEqualTo(0);
        assertThat(result.existingDetail).isNull();
    }

    @Test
    void shouldIncreaseMajorWhenNoCompatibleHistory() throws Exception {
        List<MoParamsDetail> history = new ArrayList<>();
        history.add(detail(1, 0, 0, "{\"old\":1}"));

        VersionResultView result = invokeDetermineVersion(history, jsonNode("{\"new\":1}"));

        assertThat(result.major).isEqualTo(2);
        assertThat(result.minor).isEqualTo(0);
        assertThat(result.patch).isEqualTo(0);
        assertThat(result.existingDetail).isNull();
    }

    @Test
    void shouldIncreaseMinorWhenCurrentHasAdditionalFields() throws Exception {
        List<MoParamsDetail> history = new ArrayList<>();
        history.add(detail(1, 0, 0, "{\"base\":1}"));

        VersionResultView result = invokeDetermineVersion(history, jsonNode("{\"base\":1,\"extra\":2}"));

        assertThat(result.major).isEqualTo(1);
        assertThat(result.minor).isEqualTo(1);
        assertThat(result.patch).isEqualTo(0);
        assertThat(result.existingDetail).isNull();
    }

    @Test
    void shouldReturnExistingVersionWhenParamsMatchExactly() throws Exception {
        List<MoParamsDetail> history = new ArrayList<>();
        MoParamsDetail matching = detail(1, 2, 3, "{\"match\":true}" );
        history.add(matching);

        VersionResultView result = invokeDetermineVersion(history, jsonNode("{\"match\":true}"));

        assertThat(result.major).isEqualTo(1);
        assertThat(result.minor).isEqualTo(2);
        assertThat(result.patch).isEqualTo(3);
        assertThat(result.existingDetail).isEqualTo(matching);
    }

    @Test
    void shouldIncreasePatchWhenStructureMatchesButContentDiffers() throws Exception {
        List<MoParamsDetail> history = new ArrayList<>();
        history.add(detail(1, 1, 1, "{\"value\":1}"));

        VersionResultView result = invokeDetermineVersion(history, jsonNode("{\"value\":2}"));

        assertThat(result.major).isEqualTo(1);
        assertThat(result.minor).isEqualTo(1);
        assertThat(result.patch).isEqualTo(2);
        assertThat(result.existingDetail).isNull();
    }

    private VersionResultView invokeDetermineVersion(List<MoParamsDetail> history, JsonNode current) throws Exception {
        Object rawResult = determineVersionMethod.invoke(moParamsDetailService, history, current);
        Class<?> resultClass = rawResult.getClass();

        Field major = resultClass.getDeclaredField("major");
        Field minor = resultClass.getDeclaredField("minor");
        Field patch = resultClass.getDeclaredField("patch");
        Field existingDetail = resultClass.getDeclaredField("existingDetail");

        major.setAccessible(true);
        minor.setAccessible(true);
        patch.setAccessible(true);
        existingDetail.setAccessible(true);

        return new VersionResultView(
                (int) major.get(rawResult),
                (int) minor.get(rawResult),
                (int) patch.get(rawResult),
                (MoParamsDetail) existingDetail.get(rawResult)
        );
    }

    private MoParamsDetail detail(int major, int minor, int patch, String params) {
        MoParamsDetail detail = new MoParamsDetail();
        detail.setVersionMajor(major);
        detail.setVersionMinor(minor);
        detail.setVersionPatch(patch);
        detail.setParams(params);
        return detail;
    }

    private JsonNode jsonNode(String json) throws Exception {
        return objectMapper.readTree(json);
    }

    private static class VersionResultView {
        final int major;
        final int minor;
        final int patch;
        final MoParamsDetail existingDetail;

        VersionResultView(int major, int minor, int patch, MoParamsDetail existingDetail) {
            this.major = major;
            this.minor = minor;
            this.patch = patch;
            this.existingDetail = existingDetail;
        }
    }
}

