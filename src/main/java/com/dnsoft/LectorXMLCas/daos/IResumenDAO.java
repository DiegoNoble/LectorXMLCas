package com.dnsoft.LectorXMLCas.daos;

import com.dnsoft.LectorXMLCas.beans.Resumen;
import com.dnsoft.LectorXMLCas.beans.ResumenPorMes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IResumenDAO extends JpaRepository<Resumen, Long> {

    @Query("SELECT new com.dnsoft.LectorXMLCas.beans.ResumenPorMes(month(r.fecha) ,sum(r.Coin_in_Total)) FROM Resumen r where r.fecha between '2016-01-01' and  '2016-12-31' and r.Sala= 'Casino Salto' group by month(r.fecha)")
    List<ResumenPorMes> coinInMensual();
    
     @Query("SELECT new com.dnsoft.LectorXMLCas.beans.ResumenPorMes(month(r.fecha) ,sum(r.Ganancia_Total)) FROM Resumen r where r.fecha between '2016-01-01' and  '2016-12-31' and r.Sala= 'Casino Salto' group by month(r.fecha)")
    List<ResumenPorMes> winMensual();

}
