//package com.ohara.corrida_colosseum.dao;
//
//import com.ohara.corrida_colosseum.dto.DashboardKPI;
//import com.ohara.corrida_colosseum.dto.EventOccupation;
//import com.ohara.corrida_colosseum.dto.TopClient;
//import com.ohara.corrida_colosseum.models.Reservation;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.Date;
//import java.util.List;
//
//public interface DashboardRepository extends JpaRepository<Reservation, Long> {
//
//
//    // KPIs principaux
//    @Query("SELECT " +
//            "new com.ohara.corrida_colosseum.dto.DashboardKPI( " +
//            "COUNT(r), " +
//            "SUM(sc.price * r.quantity), " +
//            "(SUM(r.quantity) * 100.0 / (SELECT SUM(e.totalCapacity) FROM Event e WHERE e.date > CURRENT_DATE)), " +
//            "(SELECT COUNT(e) FROM Event e WHERE e.date > CURRENT_DATE) " +
//            ") " +
//            "FROM Reservation r " +
//            "JOIN r.seatCategory sc " +
//            "WHERE r.reservationDate BETWEEN :start AND :end")
//    DashboardKPI getDashboardKPIs(@Param("start") Date start,
//                                  @Param("end") Date end);
//
//    // Occupation par événement
//    @Query("SELECT " +
//            "new com.ohara.corrida_colosseum.dto.EventOccupation( " +
//            "e.name, " +
//            "e.date, " +
//            "SUM(r.quantity), " +
//            "e.totalCapacity, " +
//            "(SUM(r.quantity) * 100.0 / e.totalCapacity) " +
//            ") " +
//            "FROM Event e " +
//            "LEFT JOIN Reservation r ON r.event = e " +
//            "WHERE e.date > CURRENT_DATE " +
//            "GROUP BY e.id")
//    List<EventOccupation> getEventsOccupation();
//
//    // Top clients
//    @Query("SELECT " +
//            "new com.ohara.corrida_colosseum.dto.TopClient( " +
//            "u.firstName, " +
//            "u.email, " +
//            "COUNT(r), " +
//            "SUM(sc.price * r.quantity) " +
//            ") " +
//            "FROM Reservation r " +
//            "JOIN r.user u " +
//            "JOIN r.seatCategory sc " +
//            "GROUP BY u.id " +
//            "ORDER BY COUNT(r) DESC " +
//            "LIMIT 5")
//    List<TopClient> getTopClients();
//}
