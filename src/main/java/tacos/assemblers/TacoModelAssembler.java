package tacos.assemblers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.controllers.api.ApiDesignTacoController;
import tacos.dto.TacoModel;
import tacos.entities.Taco;


public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {


    public TacoModelAssembler() {
        super(ApiDesignTacoController.class, TacoModel.class);
    }

    @Override
    protected TacoModel instantiateModel(Taco entity) {
        return new  TacoModel(entity);
    }

    @Override
    public TacoModel toModel(Taco entity) {
        return createModelWithId(entity.getId(),entity);
    }

}
