package com.CompanieTurism.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "documente")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Document {
}
