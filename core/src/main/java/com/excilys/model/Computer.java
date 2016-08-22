package com.excilys.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "computer")
public class Computer implements IComputer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate introduced;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate discontinued;
    @ManyToOne
    @JoinColumn
    private Company company;

    public Computer() {
    }

    /**
     * @param builder builder
     */
    public Computer(Builder builder) {
        id = builder.computer.id;
        name = builder.computer.name;
        introduced = builder.computer.introduced;
        discontinued = builder.computer.discontinued;
        company = builder.computer.company;
    }

    public static class Builder {

        private Computer computer = new Computer();

        /**
         * @param id id to be built
         * @return builder builder
         */
        public Builder id(Long id) {
            computer.id = id;
            return this;
        }

        /**
         * @param name name to be built
         * @return builder builder
         */
        public Builder name(String name) {
            computer.name = name;
            return this;
        }

        /**
         * @param introduced date to be built
         * @return builder builder
         */
        public Builder introduced(LocalDate introduced) {
            computer.introduced = introduced;
            return this;
        }

        /**
         * @param discontinued data to be build
         * @return builder builder
         */
        public Builder discontinued(LocalDate discontinued) {
            computer.discontinued = discontinued;
            return this;
        }

        /**
         * @param company company to be built
         * @return builder builder
         */
        public Builder company(Company company) {
            computer.company = company;
            return this;
        }

        /**
         * Call to constructor with builder.
         *
         * @return Computer computer built
         */
        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public LocalDate getIntroduced() {
        return introduced;
    }

    @Override
    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    @Override
    public LocalDate getDiscontinued() {
        return discontinued;
    }

    @Override
    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public Company getCompany() {
        return company;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Computer other = (Computer) obj;
        if (company == null) {
            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
            return false;
        }
        if (discontinued == null) {
            if (other.discontinued != null) {
                return false;
            }
        } else if (!discontinued.equals(other.discontinued)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (introduced == null) {
            if (other.introduced != null) {
                return false;
            }
        } else if (!introduced.equals(other.introduced)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced
                + ", discontinued=" + discontinued + ", company=" + company + "]";
    }

}
