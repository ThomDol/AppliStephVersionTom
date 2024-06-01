package org.gestion_patient.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MesurerId implements java.io.Serializable {
    private static final long serialVersionUID = -262618316988994648L;
    @Column(name = "id_physique", nullable = false)
    private int idPhysique;

    @Column(name = "id_patient", nullable = false)
    private int idPatient;

    @Override
    public String toString(){
        return "idPhysique "+idPhysique+" & idPatient "+idPatient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MesurerId entity = (MesurerId) o;
        return Objects.equals(this.idPhysique, entity.idPhysique) &&
                Objects.equals(this.idPatient, entity.idPatient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPhysique, idPatient);
    }

}