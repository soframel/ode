package org.soframel.opendata.ode.repository.frpar.elastic;

import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.dto.frpar.MandatDTO;
import org.soframel.opendata.ode.mapping.ODEMapper;
import org.soframel.opendata.ode.repository.ODERepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CompositeMandatRepository implements ODERepository<Mandat> {

	@Autowired
	private ODEMapper<Mandat, MandatDTO> mandatMapper;
	@Autowired
	private ODERepository<MandatDTO> mandatRepository;

	@Override
	public void save(Mandat o) throws Exception {
		MandatDTO dto = mandatMapper.map(o);
		mandatRepository.save(dto);
	}

	@Override
	public Mandat get(String id) throws Exception {
		MandatDTO dto = mandatRepository.get(id);
		Mandat vo = mandatMapper.mapInverted(dto);
		return vo;
	}

	@Override
	public void deleteAll() throws Exception {
		mandatRepository.deleteAll();
	}

	@Override
	public void createIndexMapping() throws Exception {
		mandatRepository.createIndexMapping();
	}

	@Override
	public Mandat getCached(String id) {
		throw new IllegalStateException("Not implemented");
	}

	@Override
	public void cache(Mandat o) {
		throw new IllegalStateException("Not implemented");
	}

}
