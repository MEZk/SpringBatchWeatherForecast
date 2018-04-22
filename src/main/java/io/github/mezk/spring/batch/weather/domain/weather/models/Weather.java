package io.github.mezk.spring.batch.weather.domain.weather.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import io.github.mezk.spring.batch.weather.domain.weather.models.enums.Units;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue
    @Column(name = "weather_id")
    private UUID weather_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "units")
    private Units units;

    @Column(name = "temperature")
    private double temperature;

    @OneToOne
    @JoinColumn(name = "city_id")
    @Cascade(CascadeType.ALL)
    private City city;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @PrePersist
    private void initCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    @PreUpdate
    private void updateModificationDate() {
        this.modificationDate = LocalDateTime.now();
    }

}
