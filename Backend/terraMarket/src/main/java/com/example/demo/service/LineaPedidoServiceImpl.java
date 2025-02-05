package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.dto.LineaPedidoDTO;
import com.example.demo.model.dto.PedidoDTO;
import com.example.demo.repository.dao.LineaPedidoRepository;
import com.example.demo.repository.dao.PedidoRepository;
import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.LineaPedido;

@Service
public class LineaPedidoServiceImpl implements LineaPedidoService {

    private static final Logger log = LoggerFactory.getLogger(LineaPedidoServiceImpl.class);

    @Autowired
    LineaPedidoRepository lineaPedidoRepository;
    @Autowired
    PedidoService pedidoService;

    @Override
    public List<LineaPedidoDTO> findAllByPedido(PedidoDTO pedidoDTO) {

        // buscamos la lista en el repositorio
        List<LineaPedido> listaLineasPedido = lineaPedidoRepository.findAllByPedido(pedidoDTO.getId());
        log.info(PedidoServiceImpl.class.getSimpleName() + " - lista lineas pedido repositorio: " + listaLineasPedido);

        // la pasamos a dto
        List<LineaPedidoDTO> listaLineasPedidoDTO = new ArrayList<LineaPedidoDTO>();

        for (int i = 0; i < listaLineasPedido.size(); ++i) {
            LineaPedidoDTO lpDTO = LineaPedidoDTO.convertToDTO(listaLineasPedido.get(i), pedidoDTO);
            listaLineasPedidoDTO.add(lpDTO);
        }

        return listaLineasPedidoDTO;
    }

    @Override
    public LineaPedidoDTO editarLineaPedido(Long cantidad, Long idLineaPedido, ClienteDTO clienteDTO) {
        log.info("LineaPedidoServiceImpl - editarLineaPedido: " + idLineaPedido + " añadiendo: " + cantidad);
        LineaPedido lineaPedido = lineaPedidoRepository.findById(idLineaPedido).get();
        if (cantidad < 0 && lineaPedido.getCantidad() + cantidad <= 0) {
            // si la cantidad es negativa y la cantidad resultante es 0 o negativa,
            // eliminamos la
            // linea
            lineaPedidoRepository.delete(lineaPedido);
            return null;

        } else {
            lineaPedido.setCantidad(lineaPedido.getCantidad() + cantidad);
            lineaPedidoRepository.save(lineaPedido);
            PedidoDTO pedidoDTO = new PedidoDTO();
            pedidoDTO.setId(lineaPedido.getPedido().getId());
            return LineaPedidoDTO.convertToDTO(lineaPedido,
                    pedidoService.findById(pedidoDTO, clienteDTO));
        }

    }

}
