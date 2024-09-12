package com.r2s.java_backend_04.model;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class TKB {
	
//	@EmbeddedId
//	private TKBId id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
//	@MapsId(value = "courseId")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
//	@MapsId(value = "userId")
	private User user;
	
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
}

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Embeddable
//class TKBId{
//	private Integer userId;
//	private Integer courseId;
////	private String semester;
//}
