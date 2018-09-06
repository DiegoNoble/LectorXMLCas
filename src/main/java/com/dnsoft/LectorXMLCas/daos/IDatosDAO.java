package com.dnsoft.LectorXMLCas.daos;

import com.dnsoft.LectorXMLCas.beans.Datos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDatosDAO extends JpaRepository<Datos, Long> {

    @Query("select d from Datos d order by fecha asc")
    List<Datos> findAllOrderByFecha();
    
  }
