package ru.rec.cmev.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class MapWrapper {
    private List<MapEntry> entries = new ArrayList<>();

    public MapWrapper() {}

    public MapWrapper(LinkedHashMap<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            entries.add(new MapEntry(entry.getKey(), entry.getValue()));
        }
    }

    @XmlElement(name = "entry")
    public List<MapEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<MapEntry> entries) {
        this.entries = entries;
    }

    public LinkedHashMap<String, Object> toMap() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (MapEntry entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}

class MapEntry {
    private String key;
    private Object value;

    public MapEntry() {}

    public MapEntry(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @XmlElement
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @XmlElement
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
