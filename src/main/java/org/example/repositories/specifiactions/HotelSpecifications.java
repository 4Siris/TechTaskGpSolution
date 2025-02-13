package org.example.repositories.specifiactions;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.models.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class HotelSpecifications {
    public static Specification<Hotel> all(){
        return new Specification<Hotel>() {
            @Override
            public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("id"),root.get("id"));
            }
        };
    }
    public static Specification<Hotel> equalName (String hotelName){
        return new Specification<Hotel>() {
            @Override
            public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"),hotelName);
            }
        };
    }
    public static Specification<Hotel> equalBrand (String hotelBrand){
        return new Specification<Hotel>() {
            @Override
            public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("brand"),hotelBrand);
            }
        };
    }
    public static Specification<Hotel> equalCity (String hotelCity){
        return new Specification<Hotel>() {
            @Override
            public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("address").get("city"),
                        criteriaBuilder.literal(hotelCity));
            }
        };
    }
    public static Specification<Hotel> equalCounty (String hotelCounty){
        return new Specification<Hotel>() {
            @Override
            public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("address").get("county"),
                        criteriaBuilder.literal(hotelCounty));
            }
        };
    }
    public static Specification<Hotel> hasAmenities (List<String> hotelAmenities){
        return new Specification<Hotel>() {
            @Override
            public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate[] predicates = hotelAmenities.stream()
                        .map(amenity -> criteriaBuilder.isMember(amenity, root.get("amenities")))
                        .toArray(Predicate[]::new);
                return criteriaBuilder.and(predicates);
            }
        };
    }
}
