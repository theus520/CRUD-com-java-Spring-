package domain.product;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor 
@EqualsAndHashCode(of = "id")

public class Product {
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY)
	private String id;
	private String name;
	private Number prince_in_cents;
	
}
