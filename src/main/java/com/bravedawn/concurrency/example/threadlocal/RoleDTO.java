package com.bravedawn.concurrency.example.threadlocal;

/**
 * @Author : fengx9
 * @Project : jvm-demo
 * @Date : Created in 2024-03-14 16:49
 */
public class RoleDTO {

    private Long id;
    private String name;

    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
