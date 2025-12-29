package com.project.hospital_management.dto;

import com.project.hospital_management.entity.type.BloodGroupType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodGroupTypeResponseEntity {
    private BloodGroupType bloodGroup;
    private Long count;
}
