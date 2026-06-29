package utp.edu.pe.proyectodp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.proyectodp.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}