package com.excilys.model;

public class Company {

    private long id;
    private String name;

    public Company() {
        id = -1;
        name = null;
    }

    public Company(Builder builder) {
        id = builder.company.id;
        name = builder.company.name;
    }

    public Company(Company company) {
        id = company.id;
        name = company.name;
    }

    public static class Builder {
        private Company company = new Company();

        public Builder id(long id) {
            company.id = id;
            return this;
        }

        public Builder name(String name) {
            company.name = name;
            return this;
        }

        public Company build() {
            return new Company(this);
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Company other = (Company) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Company [id=" + id + ", name=" + name + "]";
    }

}
